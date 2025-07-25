package es.ua.eps.carkier.Carnets

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import es.ua.eps.carkier.Contratos.VerContratos
import es.ua.eps.carkier.CrearCuenta.CrearCarnetDeConducir
import es.ua.eps.carkier.CrearCuenta.ModificarCarnets
import es.ua.eps.carkier.Filtro.FilterActivity
import es.ua.eps.carkier.InicioSesion
import es.ua.eps.carkier.Modelos.CarnetConducir
import es.ua.eps.carkier.Modelos.Vehiculos
import es.ua.eps.carkier.Principal
import es.ua.eps.carkier.R
import es.ua.eps.carkier.Retrofit.RetrofitClient
import es.ua.eps.carkier.adapter.CarnetAdapter
import es.ua.eps.carkier.adapter.VehiculoAdapter
import es.ua.eps.carkier.databinding.ActivityMostrarCarnetsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MostrarCarnets : AppCompatActivity() {
    private lateinit var binding: ActivityMostrarCarnetsBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarCarnetsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recuperar nombre y correo del SharedPreferences
        sharedPreferences = getSharedPreferences("usuario", MODE_PRIVATE)
        cargarDatos()

        val nombreUsuario = sharedPreferences.getLong("id", 0)
        comprobarExistente(nombreUsuario)

        binding.btnAgregar.setOnClickListener() {
            val intent = Intent(this, CrearCarnetDeConducir::class.java)
            startActivity(intent)
        }

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
                R.id.CerrarSesion -> {
                    cerrarSesion()
                    true
                }

                R.id.contrato -> {
                    val intent = Intent(this, VerContratos::class.java)
                    startActivity(intent)
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
    }

    fun setTheme(isDarkMode: Boolean) {
        if (isDarkMode) {
            // Establecer modo oscuro
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

            // Actualizar los colores del NavigationView para el modo oscuro
            binding.navigationView.setItemTextColor(ColorStateList.valueOf(Color.WHITE))
            binding.navigationView.setItemIconTintList(ColorStateList.valueOf(Color.WHITE))
        } else {
            // Establecer modo claro
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            // Actualizar los colores del NavigationView para el modo claro
            binding.navigationView.setItemTextColor(ColorStateList.valueOf(Color.BLACK))
            binding.navigationView.setItemIconTintList(ColorStateList.valueOf(Color.BLACK))
        }

        // Guardar la preferencia del usuario
        val editor = sharedPreferences.edit()
        editor.putBoolean("dark_mode", isDarkMode)
        editor.apply()
    }

    fun cargarDatos() {
        val nombreUsuario = sharedPreferences.getString("nombre", "sin Nombre")
        val correoUsuario = sharedPreferences.getString("correo", "correo@ejemplo.com")
        var puntosUsario = sharedPreferences.getString("puntos", "0 CarPoints")
        puntosUsario = puntosUsario + " CarPoints"
        // Obtener el header del NavigationView
        val headerView = binding.navigationView.getHeaderView(0)
        val nombreTextView: TextView = headerView.findViewById(R.id.nombre)
        val correoTextView: TextView = headerView.findViewById(R.id.correo)
        val puntosTextView: TextView = headerView.findViewById(R.id.puntos)

        // Asignar los valores recuperados a los TextViews del header
        nombreTextView.text = nombreUsuario
        correoTextView.text = correoUsuario
        puntosTextView.text = puntosUsario

    }

    fun comprobarExistente(idUsuario: Long) {
        // Llamada a la API para obtener los carnets del usuario
        RetrofitClient.instance.CarnetsPersona(idUsuario)
            .enqueue(object : Callback<List<CarnetConducir>> {

                override fun onResponse(
                    call: Call<List<CarnetConducir>>,
                    response: Response<List<CarnetConducir>>
                ) {
                    if (response.isSuccessful) {
                        // Si la respuesta es exitosa, obtener la lista de carnets
                        val carnets = response.body() ?: emptyList()

                        if (carnets.isEmpty()) {
                            Toast.makeText(
                                this@MostrarCarnets,
                                "No hay carnets disponibles para este usuario",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            // Cargar la lista de carnets en alguna parte de la interfaz (por ejemplo, un RecyclerView)
                            cargarLista(carnets)
                        }
                    } else {
                        // Manejar la respuesta no exitosa, mostrar mensaje de error
                        Toast.makeText(
                            this@MostrarCarnets,
                            "Error en la respuesta: ${response.message()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<List<CarnetConducir>>, t: Throwable) {
                    // Maneja errores de conexión
                    Toast.makeText(
                        this@MostrarCarnets,
                        "Error de conexión: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("ErrorAPI", "Error en la llamada a la API: ${t.message}", t)
                }
            })
    }

    fun cargarLista(carnets: List<CarnetConducir>) {
        // RecyclerView configuration
        binding.ListaCarnet.layoutManager = GridLayoutManager(this, 1)

        // Crear el adaptador y pasar el callback para el clic
        val adapter = CarnetAdapter(carnets) { carnet ->
            // Esta es la acción que se realiza cuando se hace clic en un item
            val intent = Intent(this, ModificarCarnets::class.java).apply {
                putExtra("idCarnet", carnet.id) // Enviar el ID del carnet
                putExtra("idUsuarios", carnet.idusuario) // Enviar el ID del usuario
                putExtra("fechaExpedicion", carnet.fechaExpedicion) // Enviar la fecha
                putExtra("tipo", carnet.idTipocarnet) // Enviar el ID del tipo
            }
            startActivity(intent)
        }

        binding.ListaCarnet.adapter = adapter
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

}