package es.ua.eps.carkier

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import java.io.ByteArrayOutputStream
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import es.ua.eps.carkier.Carnets.MostrarCarnets
import es.ua.eps.carkier.Contratos.VerContratos
import es.ua.eps.carkier.Filtro.FilterActivity
import es.ua.eps.carkier.Modelos.DatosUsuarios
import es.ua.eps.carkier.Modelos.Usuarios
import es.ua.eps.carkier.Modelos.Vehiculos
import es.ua.eps.carkier.Retrofit.RetrofitClient
import es.ua.eps.carkier.adapter.VehiculoAdapter
import es.ua.eps.carkier.databinding.ActivityPrincipalBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Principal : AppCompatActivity() {
    private lateinit var binding: ActivityPrincipalBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var sharedPreferences: SharedPreferences

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recuperar nombre y correo del SharedPreferences
        sharedPreferences = getSharedPreferences("usuario", MODE_PRIVATE)
        sharedPreferences = getSharedPreferences("correo", MODE_PRIVATE)

        // Mostrar el ProgressBar antes de cargar los datos
        showProgressBar()

        //Cargamos el recylcevie de vehiuclos
        comprobarExistente()

        // Cargar preferencias para el modo
        sharedPreferences = getSharedPreferences("usuario", MODE_PRIVATE)

        // Comprobar el modo previamente guardado
        val isDarkMode = sharedPreferences.getBoolean("dark_mode", false)
        setTheme(isDarkMode)

        // Inicializa el DrawerLayout
        drawerLayout = binding.drawerLayout

        // Para la funcionalidad de los botones del menu de abajo
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.principal -> {
                    startActivity(Intent(this, Principal::class.java))
                    true
                }

                R.id.filtrarBusqueda -> {
                    startActivity(Intent(this, FilterActivity::class.java))
                    true
                }

                R.id.perfil -> {
                    // Abre el Navigation Drawer cuando se pulsa en nav_search
                    drawerLayout.openDrawer(GravityCompat.START)
                    true
                }

                else -> false
            }
        }

        // Configuración del NavigationView usando binding
        binding.navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.carnet -> {
                    val intent = Intent(this, MostrarCarnets::class.java)
                    startActivity(intent)
                    true
                }

                R.id.contrato -> {
                    val intent = Intent(this, VerContratos::class.java)
                    startActivity(intent)
                    true
                }

                R.id.CerrarSesion -> {
                    cerrarSesion()
                    true
                }

                R.id.modoNocturno -> {
                    // Cambiar entre modo oscuro y modo claro
                    val isCurrentlyDarkMode = sharedPreferences.getBoolean("dark_mode", false)
                    if (isCurrentlyDarkMode) {
                        setTheme(false)  // Cambiar a modo claro
                    } else {
                        setTheme(true)  // Cambiar a modo oscuro
                    }
                    true
                }

                R.id.contactoTelefono -> {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:637 65 02 50")
                    startActivity(intent)
                    true
                }
                R.id.contactoCorreo -> {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:carkier@email.com")
                    }
                    startActivity(Intent.createChooser(intent, "Enviar correo con..."))
                    true
                }
                // Otros ítems pueden ser manejados aquí
                else -> false
            }
        }

        val idUsuario = sharedPreferences.getLong("id", 0)

        if (idUsuario.toInt() != 0) {
            cargarDatosUsuarios(idUsuario) { datos ->
                if (datos != null) {
                    println("Usuario puntos: ${datos.puntos}")

                    val editor = sharedPreferences.edit()
                    editor.putString("puntos", datos.puntos.toString())  // Clave: "puntos", Valor: "100"
                    editor.apply()  // Aplica los cambios de manera asíncrona

                    cargarDatos()
                } else {
                    println("Error al cargar datos del usuario")
                    cargarDatos()
                }
            }
        }
    }

    fun setTheme(isDarkMode: Boolean) {
        if (isDarkMode) {
            // Establecer modo oscuro
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            // Establecer modo claro
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        // Guardar la preferencia del usuario
        val editor = sharedPreferences.edit()
        editor.putBoolean("dark_mode", isDarkMode)
        editor.apply()
    }

    fun cargarDatos() {
        val nombreUsuario = sharedPreferences.getString("nombre", "")
        val correoUsuario = sharedPreferences.getString("correo", "correo@ejemplo.com")
        var puntosUsario = sharedPreferences.getString("puntos", "0 CarPoints")
        puntosUsario = puntosUsario+ " CarPoints"
        val headerView =
            binding.navigationView.getHeaderView(0) // Obtener el header del NavigationView
        val nombreTextView: TextView = headerView.findViewById(R.id.nombre)
        val correoTextView: TextView = headerView.findViewById(R.id.correo)
        val puntosTextView: TextView = headerView.findViewById(R.id.puntos)

        // Asignar los valores recuperados a los TextViews del header
        nombreTextView.text = nombreUsuario
        correoTextView.text = correoUsuario
        puntosTextView.text = puntosUsario

    }

    fun cargarLista(vehiculos: List<Vehiculos>) {
        // RecyclerView configuration
        binding.recyclerViewVehiculos.layoutManager = GridLayoutManager(this, 1)
        // Asignar el adaptador
        val adapter = VehiculoAdapter(vehiculos)
        binding.recyclerViewVehiculos.adapter = adapter
        // Mostrar el ProgressBar antes de cargar los datos
    }

    fun comprobarExistente() {
        // Llamada a la API para obtener los vehículos
        RetrofitClient.instance.mostrarVehiculos().enqueue(object : Callback<List<Vehiculos>> {

            override fun onResponse(
                call: Call<List<Vehiculos>>,
                response: Response<List<Vehiculos>>
            ) {
                if (response.isSuccessful) {
                    // Si la respuesta es exitosa, obtener la lista de vehículos
                    val vehiculos = response.body() ?: emptyList()
                    cargarLista(vehiculos)
                    hideProgressBar()
                } else {
                    // Maneja la respuesta no exitosa

                }
            }

            override fun onFailure(call: Call<List<Vehiculos>>, t: Throwable) {
                // Maneja errores de conexión
                Toast.makeText(this@Principal, "Fallo: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun cerrarSesion() {
        // Elimina las preferencias compartidas
        val sharedPreferences = getSharedPreferences("usuario", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear() // Borra todas las preferencias
        editor.apply()

        // Redirige al usuario a la actividad de inicio de sesión
        val intent = Intent(this, InicioSesion::class.java)
        startActivity(intent)
        finish() // Termina la actividad actual para que el usuario no pueda regresar a ella
    }
    

    fun cargarDatosUsuarios(idUsuario: Long, callback: (DatosUsuarios?) -> Unit) {
        // Asegúrate de que la llamada de Retrofit esté configurada correctamente
        RetrofitClient.instance.datosUsuarios(idUsuario).enqueue(object : Callback<DatosUsuarios> {

            override fun onResponse(call: Call<DatosUsuarios>, response: Response<DatosUsuarios>) {
                if (response.isSuccessful) {
                    // Log para verificar la respuesta de la API
                    Log.d("Retrofit", "Datos de usuario obtenidos: ${response.body()}")
                    callback(response.body()) // Llamar al callback con los datos
                } else {
                    // Log para respuesta no exitosa
                    Log.e("Retrofit", "Error en la respuesta: ${response.code()}")
                    callback(null) // Llamar al callback con null si hay un error
                }
            }

            override fun onFailure(call: Call<DatosUsuarios>, t: Throwable) {
                // Log para verificar error de la llamada (sin conexión, etc.)
                Log.e("Retrofit", "Error en la conexión: ${t.message}")
                callback(null) // Llamar al callback con null si la llamada falla
            }
        })
    }


}