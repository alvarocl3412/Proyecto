package es.ua.eps.carkier

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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

        // Mostrar el ProgressBar antes de cargar los datos
        showProgressBar()
        cargarDatos()

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
                        modificarVehiculo()
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
                // Otros ítems pueden ser manejados aquí
                else -> false
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

    fun cargarDatos(){

        val nombreUsuario = sharedPreferences.getString("nombre","")
        val correoUsuario = sharedPreferences.getString("correo", "correo@ejemplo.com")

        val headerView = binding.navigationView.getHeaderView(0) // Obtener el header del NavigationView
        val nombreTextView: TextView = headerView.findViewById(R.id.nombre)
        val correoTextView: TextView = headerView.findViewById(R.id.correo)

        // Asignar los valores recuperados a los TextViews del header
        nombreTextView.text = nombreUsuario
        correoTextView.text = correoUsuario

    }

    fun cargarLista(vehiculos: List<Vehiculos>){
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

            override fun onResponse(call: Call<List<Vehiculos>>, response: Response<List<Vehiculos>>) {
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

    fun modificarVehiculo(){

        val imagenBase64 = convertirImagenABase64(R.drawable.logo) // O cualquier imagen que quieras usar

        val v = Vehiculos(
            id = 16,
            idEmpresa = null, // Asegúrate de que esto sea permitido en tu clase
            idUsuariosPropietario = 15,
            idEstado = 1,
            matricula = "840 DFG",
            marca = "Honda",
            modelo = "Civic Type R",
            anio = 1995,
            km = 12121,
            precioventa = null, // Asegúrate de usar un valor decimal si es necesario
            preciodia = 70.0, // Igualmente, usa un valor decimal
            imagen = imagenBase64 ?: null
        )
        updateVehiculo(v)
    }

    fun updateVehiculo(vehiculos: Vehiculos) {
        RetrofitClient.instance.updateVehiculo(vehiculos).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@Principal, "Creado correctamente", Toast.LENGTH_SHORT).show()
                } else {
                    // Mensaje de error más detallado
                    val errorBody = response.errorBody()?.string()
                    Toast.makeText(this@Principal, "No se ha podido crear correctamente: $errorBody", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(this@Principal, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("CrearClienteError", "Error: ${t.message}", t)
            }
        })
    }

    fun convertirImagenABase64(resId: Int): String? {
        // Obtener el Bitmap del drawable
        val bitmap = BitmapFactory.decodeResource(resources, resId)

        // Comprobar si el bitmap es nulo
        if (bitmap == null) {
            return null
        }

        // Convertir el Bitmap a un Array de Bytes
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()

        // Convertir a Base64
        return Base64.encodeToString(byteArray, Base64.NO_WRAP)
    }
}