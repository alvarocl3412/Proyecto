package es.ua.eps.carkier

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import es.ua.eps.carkier.Carnets.MostrarCarnets
import es.ua.eps.carkier.Filtro.FilterActivity
import es.ua.eps.carkier.Modelos.Comentario
import es.ua.eps.carkier.Modelos.EstadoVehiculo
import es.ua.eps.carkier.Modelos.Vehiculos
import es.ua.eps.carkier.Retrofit.RetrofitClient
import es.ua.eps.carkier.adapter.ComentarioAdapter
import es.ua.eps.carkier.databinding.ActivityMostrarVehiculoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MostrarVehiculo : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var binding: ActivityMostrarVehiculoBinding
    private var vehiculo: Vehiculos? = null
    private var vehiculoId: Long? = null
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var adapter: ComentarioAdapter // Declarar aquí

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarVehiculoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("usuario", MODE_PRIVATE)

        // Obtener los datos del vehículo pasados por intent
        vehiculoId = intent.getLongExtra("idvehiculo", -1L)
        // Llamar a la función en un CoroutineScope
        if (vehiculoId != null) {
            CoroutineScope(Dispatchers.Main).launch {
                vehiculo = obtenerVehiculoPorId(vehiculoId!!.toLong())
                inicializarComentario(vehiculo!!.id)
                if (vehiculo != null) {
                    mostrarDetallesVehiculo(vehiculo!!)
                } else {
                    Toast.makeText(
                        this@MostrarVehiculo,
                        "No se pudo obtener el vehículo",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        binding.etEscribirComentario.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.layoutBotonesComentario.visibility = View.VISIBLE
            }
        }

        binding.btnCancelarComentario.setOnClickListener {
            binding.etEscribirComentario.clearFocus()
            binding.etEscribirComentario.text.clear()
            binding.layoutBotonesComentario.visibility = View.GONE
        }

        binding.btnEnviarComentario.setOnClickListener() {
            binding.layoutBotonesComentario.visibility = View.GONE
            var comentario = Comentario(
                idUsuario = sharedPreferences.getLong("id", 0L),
                idVehiculo = vehiculo!!.id,
                comentario = binding.etEscribirComentario.text.toString()
            )
            guardarComentario(comentario)
        }

        binding.btnContratar.setOnClickListener() {
            val intent = Intent(this, ContratarVehiculo::class.java)
            intent.putExtra("idvehiculo", vehiculoId)
            intent.putExtra("preciovehiculo", vehiculo?.preciodia)
            startActivity(intent)
        }


        // Inicializa el DrawerLayout
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)


        // Comprobar el modo previamente guardado
        val isDarkMode = sharedPreferences.getBoolean("dark_mode", false)
        setTheme(isDarkMode)

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

        cargarDatos()

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


    suspend fun obtenerVehiculoPorId(vehiculoId: Long): Vehiculos? {
        return try {
            val response = RetrofitClient.instance.VehiculoId(vehiculoId).awaitResponse()
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("VehiculoAPI", "Error en la petición: ${e.message}")
            null
        }
    }

    fun inicializarComentario(idVehiculo: Long) {
        RetrofitClient.instance.ComentarioVehiculo(idVehiculo)
            .enqueue(object : Callback<MutableList<Comentario>> {
                override fun onResponse(
                    call: Call<MutableList<Comentario>>,
                    response: Response<MutableList<Comentario>>
                ) {
                    if (response.isSuccessful) {
                        val comentarios: MutableList<Comentario>? = response.body()

                        // Verifica si comentarios es null o vacío
                        if (comentarios != null && comentarios.isNotEmpty()) {
                            binding.txtComent.text = "${comentarios.size} COMENTARIOS"
                            binding.recycleComentario.layoutManager =
                                LinearLayoutManager(this@MostrarVehiculo)

                            val nombreUsuario = sharedPreferences.getLong("id", 0L)

                            val adapter = ComentarioAdapter(
                                this@MostrarVehiculo,
                                comentarios.toMutableList()
                            ) { comentario, respuestaTexto ->
                                // Genera un nuevo ID para la respuesta
                                val nuevaRespuesta = Comentario(
                                    id = generarNuevoId(),
                                    idUsuario = nombreUsuario,
                                    idVehiculo = comentario.idVehiculo,
                                    idComentarioRespuesta = comentario.id,
                                    comentario = respuestaTexto,
                                    fecha = obtenerFechaActual()
                                )
                                // Agregar la nueva respuesta a la lista de comentarios
                                comentarios.add(nuevaRespuesta)
                                // Notificar al adaptador sobre el nuevo item
                                adapter.notifyItemInserted(comentarios.size - 1)
                            }

                            binding.recycleComentario.adapter = adapter
                        } else {
                            // Manejo cuando no hay comentarios
                            binding.txtComent.text = "0 COMENTARIOS"
                            Toast.makeText(
                                this@MostrarVehiculo,
                                "No hay comentarios disponibles",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@MostrarVehiculo,
                            "Error al cargar comentarios. Código: ${response.code()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<MutableList<Comentario>>, t: Throwable) {
                    Toast.makeText(
                        this@MostrarVehiculo,
                        "Error de conexión: ${t.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }

    // Implementa esta función para generar IDs únicos
    fun generarNuevoId(): Long {
        // Lógica para generar un nuevo ID (puedes usar un contador, UUID, etc.)
        return System.currentTimeMillis() // Ejemplo simple, no es recomendado para producción
    }

    // Implementa esta función para obtener la fecha actual en formato deseado
    private fun obtenerFechaActual(): String {
        val formatoSalida = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return formatoSalida.format(Date())
    }

    fun mostrarDetallesVehiculo(vehiculos: Vehiculos?) {
        EstadoNombre(vehiculos?.idEstado?.toLong()) { estado ->
            if (estado != null) {
                // Usa el nombre del usuario aquí
                binding.txtMostrarEstado.text = "Estado: ${estado}"
            }
        }
        binding.txtMostrarMatricula.text = "Matricula: ${vehiculos?.matricula}"
        binding.txtMostrarMarca.text = "Marca: ${vehiculos?.marca}"
        binding.txtMostrarModelo.text = "Modelo: ${vehiculos?.modelo}"
        binding.txtMostrarAnio.text = "Anio: ${vehiculos?.anio}"
        binding.txtMostrarKM.text = "Kilometros: ${vehiculos?.km}"
        binding.txtMostrarPrecioDia.text = "Precio/Dia: ${vehiculos?.preciodia.toString()} €"

        if (vehiculos?.precioventa == null || vehiculos.precioventa == 0.0) {
            binding.txtMostrarPrecioVenta.text = "No se vende"
        } else {
            binding.txtMostrarPrecioVenta.text = "Precio/Venta: ${vehiculos.precioventa}"
        }
        if (vehiculo!!.imagen != null) {
            if (vehiculo?.imagen != null) {
                val bitmap =
                    binding.imgVehiucloMostrar.setBase64Image(vehiculo!!.imagen.toString())
                if (bitmap != null) {
                    binding.imgVehiucloMostrar.setScaledImage(bitmap)
                } else {
                    Log.e("MostrarVehiculo", "El bitmap es nulo")
                }
            }
        }

    }

    fun EstadoNombre(id: Long?, callback: (String?) -> Unit) {
        RetrofitClient.instance.EstadoVehiculoId(id).enqueue(object : Callback<EstadoVehiculo> {
            override fun onResponse(
                call: Call<EstadoVehiculo>,
                response: Response<EstadoVehiculo>
            ) {
                if (response.isSuccessful) {
                    // Si la respuesta es exitosa, obtener el usuario
                    val estado = response.body()
                    if (estado != null) {
                        callback(estado.estado) // Retorna el nombre del usuario a través del callback
                    } else {
                        callback(null) // Maneja el caso de un cuerpo de respuesta nulo
                    }
                } else {
                    callback(null) // Maneja respuestas no exitosas
                }
            }

            override fun onFailure(call: Call<EstadoVehiculo>, t: Throwable) {
                callback(null) // Maneja el fallo de la petición
            }
        })
    }

    fun guardarComentario(comentario: Comentario) {
        RetrofitClient.instance.ComentarioRegistrar(comentario).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {

                    vehiculo?.id?.let { inicializarComentario(it) }

                    // Limpiar el campo de texto y ocultar el layout de botones
                    binding.etEscribirComentario.text.clear()
                    binding.layoutBotonesComentario.visibility = View.GONE

                } else {
                    // Mensaje de error más detallado
                    val errorBody = response.errorBody()?.string()
                    Toast.makeText(
                        this@MostrarVehiculo,
                        "No se ha podido crear correctamente: $errorBody",
                        Toast.LENGTH_SHORT
                    ).show()
                    vehiculo?.id?.let { inicializarComentario(it) }
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                vehiculo?.id?.let { inicializarComentario(it) }
                // Limpiar el campo de texto y ocultar el layout de botones
                binding.etEscribirComentario.text.clear()
                binding.layoutBotonesComentario.visibility = View.GONE
            }
        })


    }

    fun ImageView.setBase64Image(base64Image: String): Bitmap? {
        return try {
            // Eliminar el prefijo "data:image/png;base64,"
            val imageData = base64Image.substringAfter(",")
            val imageBytes = Base64.decode(imageData, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

            if (bitmap != null) {
                this.setImageBitmap(bitmap)
                bitmap // Devuelve el bitmap
            } else {
                Log.e("ImageView", "El bitmap es nulo")
                null
            }
        } catch (e: Exception) {
            Log.e("ImageView", "Error al establecer la imagen", e)
            null
        }
    }

    fun ImageView.setScaledImage(bitmap: Bitmap) {
        // Obtener el ancho y alto del ImageView
        val width = this.width
        val height = this.height

        // Escalar el bitmap al tamaño del ImageView
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true)

        // Establecer el bitmap escalado en la ImageView
        this.setImageBitmap(scaledBitmap)
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

}