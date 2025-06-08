package es.ua.eps.carkier.Contratos

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
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
import es.ua.eps.carkier.Filtro.FilterActivity
import es.ua.eps.carkier.InicioSesion
import es.ua.eps.carkier.Modelos.Contrato
import es.ua.eps.carkier.Principal
import es.ua.eps.carkier.R
import es.ua.eps.carkier.Retrofit.RetrofitClient
import es.ua.eps.carkier.adapter.ContratoAdapter
import es.ua.eps.carkier.databinding.ActivityVerContratosBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VerContratos : AppCompatActivity() {
    private lateinit var binding: ActivityVerContratosBinding
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
        binding = ActivityVerContratosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recuperar nombre y correo del SharedPreferences
        sharedPreferences = getSharedPreferences("usuario", MODE_PRIVATE)

        // Mostrar el ProgressBar antes de cargar los datos
        showProgressBar()
        cargarDatos()

        val idUsuario = sharedPreferences.getLong("id", -1)

        comprobarActivos(idUsuario.toInt())

        // Observa los cambios de los CheckBox y actualiza el RecyclerView

        binding.checkboxProgContracts.setOnCheckedChangeListener { _, isChecked ->
            manejarCheckBoxes()
            actualizarRecyclerView(idUsuario.toInt())
        }

        binding.checkboxActiveContracts.setOnCheckedChangeListener { _, isChecked ->
            manejarCheckBoxes()
            actualizarRecyclerView(idUsuario.toInt())
        }

        binding.checkboxTerminatedContracts.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Si se activa el tercer checkbox, desactiva y desmarca los otros dos
                binding.checkboxProgContracts.isChecked = false
                binding.checkboxActiveContracts.isChecked = false
                binding.checkboxProgContracts.isEnabled = false
                binding.checkboxActiveContracts.isEnabled = false
            } else {
                // Si se desactiva, habilita los otros dos checkboxes
                binding.checkboxProgContracts.isEnabled = true
                binding.checkboxActiveContracts.isEnabled = true
            }
            actualizarRecyclerView(idUsuario.toInt())
        }

        if (!binding.checkboxActiveContracts.isChecked && !binding.checkboxTerminatedContracts.isChecked) {
            // Si ambos están desmarcados, limpia la lista del RecyclerView
            limpiarRecyclerView()
        }


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
                    Toast.makeText(this, "Estas ya situado", Toast.LENGTH_SHORT).show();
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
        val putnosTextView: TextView = headerView.findViewById(R.id.puntos)

        // Asignar los valores recuperados a los TextViews del header
        nombreTextView.text = nombreUsuario
        correoTextView.text = correoUsuario
        putnosTextView.text = puntosUsario

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

    private fun manejarCheckBoxes() {
        // Verifica el estado de los dos primeros checkboxes
        if (binding.checkboxProgContracts.isChecked && binding.checkboxActiveContracts.isChecked) {
            binding.checkboxTerminatedContracts.isEnabled = false // Desactiva el tercer checkbox
            binding.checkboxTerminatedContracts.isChecked = false
        } else {
            binding.checkboxTerminatedContracts.isEnabled = true // Activa el tercer checkbox
        }
    }

    // Función para actualizar el contenido del RecyclerView según el estado de los CheckBox
    private fun actualizarRecyclerView(idUsuario: Int) {
        when {

            binding.checkboxProgContracts.isChecked &&
                    binding.checkboxActiveContracts.isChecked -> {
                limpiarRecyclerView()
                cargarProgActivos(idUsuario.toInt())
            }

            binding.checkboxActiveContracts.isChecked -> {
                limpiarRecyclerView()
                // Solo el CheckBox de activos está marcado
                comprobarActivos(idUsuario.toInt())
            }

            binding.checkboxTerminatedContracts.isChecked -> {
                limpiarRecyclerView()
                // Solo el CheckBox de terminados está marcado
                cargarTodo(idUsuario.toInt())
            }

            binding.checkboxProgContracts.isChecked -> {
                limpiarRecyclerView()
                comprobarProgramados(idUsuario.toInt())
            }

            else -> {
                // Ningún CheckBox está marcado: limpiar el RecyclerView
                limpiarRecyclerView()
                binding.checkboxTerminatedContracts.isEnabled = true

            }
        }
    }

    fun comprobarActivos(idCliente: Int) {
        // Llamada a la API para obtener los contratos
        RetrofitClient.instance.contratoEstado(1, idCliente)
            .enqueue(object : Callback<List<Contrato>> {

                override fun onResponse(
                    call: Call<List<Contrato>>,
                    response: Response<List<Contrato>>
                ) {
                    if (response.isSuccessful) {
                        // Si la respuesta es exitosa, obtener la lista de contratos
                        val contratos = response.body() ?: emptyList()
                        cargarLista(contratos)  // Método que carga la lista de contratos
                        hideProgressBar()  // Método para ocultar el progreso
                    } else {
                        // Maneja la respuesta no exitosa
                        Toast.makeText(
                            this@VerContratos,
                            "Error: ${response.code()} - ${response.message()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<List<Contrato>>, t: Throwable) {
                    // Maneja errores de conexión
                    Toast.makeText(
                        this@VerContratos,
                        "Fallo de conexión: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    fun comprobarProgramados(idCliente: Int) {
        // Llamada a la API para obtener los contratos
        RetrofitClient.instance.contratoEstado(4, idCliente)
            .enqueue(object : Callback<List<Contrato>> {

                override fun onResponse(
                    call: Call<List<Contrato>>,
                    response: Response<List<Contrato>>
                ) {
                    if (response.isSuccessful) {
                        // Si la respuesta es exitosa, obtener la lista de contratos
                        val contratos = response.body() ?: emptyList()
                        cargarLista(contratos)  // Método que carga la lista de contratos
                        hideProgressBar()  // Método para ocultar el progreso
                    } else {
                        // Maneja la respuesta no exitosa
                        Toast.makeText(
                            this@VerContratos,
                            "Error: ${response.code()} - ${response.message()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<List<Contrato>>, t: Throwable) {
                    // Maneja errores de conexión
                    Toast.makeText(
                        this@VerContratos,
                        "Fallo de conexión: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    fun cargarProgActivos(idCliente: Int) {
        val activosCall = RetrofitClient.instance.contratoEstado(1, idCliente)
        val programadosCall = RetrofitClient.instance.contratoEstado(4, idCliente)

        showProgressBar() // Mostrar ProgressBar antes de comenzar

        activosCall.enqueue(object : Callback<List<Contrato>> {
            override fun onResponse(
                call: Call<List<Contrato>>,
                response: Response<List<Contrato>>
            ) {
                val activos = response.body() ?: emptyList() // Obtener activos o lista vacía
                programadosCall.enqueue(object : Callback<List<Contrato>> {
                    override fun onResponse(
                        call: Call<List<Contrato>>,
                        response: Response<List<Contrato>>
                    ) {
                        val programados =
                            response.body() ?: emptyList() // Obtener programados o lista vacía
                        val combinados = activos + programados // Combinar ambas listas
                        cargarLista(combinados) // Actualizar RecyclerView con la lista combinada
                        hideProgressBar() // Ocultar ProgressBar
                    }

                    override fun onFailure(call: Call<List<Contrato>>, t: Throwable) {
                        Toast.makeText(
                            this@VerContratos,
                            "Error al cargar contratos programados: ${t.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                        hideProgressBar() // Ocultar ProgressBar en caso de error
                    }
                })
            }

            override fun onFailure(call: Call<List<Contrato>>, t: Throwable) {
                Toast.makeText(
                    this@VerContratos,
                    "Error al cargar contratos activos: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
                hideProgressBar() // Ocultar ProgressBar en caso de error
            }
        })
    }

    fun cargarTodo(idCliente: Int) {
        // Llamada a la API para obtener los contratos
        RetrofitClient.instance.contratosCliente(idCliente)
            .enqueue(object : Callback<List<Contrato>> {

                override fun onResponse(
                    call: Call<List<Contrato>>,
                    response: Response<List<Contrato>>
                ) {
                    if (response.isSuccessful) {
                        // Si la respuesta es exitosa, obtener la lista de contratos
                        val contratos = response.body() ?: emptyList()
                        cargarLista(contratos)  // Método que carga la lista de contratos
                        hideProgressBar()  // Método para ocultar el progreso
                    } else {
                        // Maneja la respuesta no exitosa
                        Toast.makeText(
                            this@VerContratos,
                            "Error: ${response.code()} - ${response.message()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<List<Contrato>>, t: Throwable) {
                    // Maneja errores de conexión
                    Toast.makeText(
                        this@VerContratos,
                        "Fallo de conexión: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    fun cargarLista(contrato: List<Contrato>) {
        // RecyclerView configuration
        binding.recyclerViewContratos.layoutManager = GridLayoutManager(this, 1)
        // Asignar el adaptador
        val adapter = ContratoAdapter(contrato)
        binding.recyclerViewContratos.adapter = adapter
        // Mostrar el ProgressBar antes de cargar los datos
    }

    // Método para limpiar la lista del RecyclerView
    private fun limpiarRecyclerView() {
        val emptyList = emptyList<Contrato>() // Lista vacía
        val adapter = ContratoAdapter(emptyList) // Crea un adaptador con la lista vacía
        binding.recyclerViewContratos.adapter = adapter // Asigna el adaptador vacío al RecyclerView
    }
}


