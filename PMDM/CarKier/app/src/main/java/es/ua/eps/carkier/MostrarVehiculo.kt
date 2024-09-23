package es.ua.eps.carkier

import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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

    private lateinit var binding: ActivityMostrarVehiculoBinding
    private var vehiculo: Vehiculos? = null
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var adapter: ComentarioAdapter // Declarar aquí


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarVehiculoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("usuario", MODE_PRIVATE)

        // Obtener los datos del vehículo pasados por intent
        val vehiculoId: Long? = intent.getLongExtra("idvehiculo", -1L)
        // Llamar a la función en un CoroutineScope
        if (vehiculoId != null) {
            CoroutineScope(Dispatchers.Main).launch {
                vehiculo = obtenerVehiculoPorId(vehiculoId)
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
        }

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
                override fun onResponse(call: Call<MutableList<Comentario>>, response: Response<MutableList<Comentario>>) {
                    if (response.isSuccessful) {
                        val comentarios: MutableList<Comentario>? = response.body()
                        if (comentarios != null && comentarios.isNotEmpty()) {
                            binding.txtComent.text = "${comentarios.size} COMENTARIOS"
                            binding.recycleComentario.layoutManager = LinearLayoutManager(this@MostrarVehiculo)

                            val nombreUsuario = sharedPreferences.getLong("id",0L)

                            val adapter = ComentarioAdapter(comentarios.toMutableList()) { comentario, respuestaTexto ->
                                // Genera un nuevo ID para la respuesta
                                val nuevaRespuesta = Comentario(
                                    id = generarNuevoId(), // Implementa esta función para generar IDs únicos
                                    idUsuario = nombreUsuario, // Reemplaza con el ID real del usuario
                                    idVehiculo = comentario.idVehiculo,
                                    idComentarioRespuesta = comentario.id,
                                    comentario = respuestaTexto,
                                    fecha = obtenerFechaActual() // Asegúrate de implementar esta función
                                )
                                // Agregar la nueva respuesta a la lista de comentarios
                                comentarios.add(nuevaRespuesta)
                                // Notificar al adaptador sobre el nuevo item
                                adapter.notifyItemInserted(comentarios.size - 1)
                            }

                            binding.recycleComentario.adapter = adapter
                        } else {
                            binding.txtComent.text = "0 COMENTARIOS"
                            Toast.makeText(this@MostrarVehiculo, "No hay comentarios disponibles", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@MostrarVehiculo, "Error al cargar comentarios. Código: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<MutableList<Comentario>>, t: Throwable) {
                    Toast.makeText(this@MostrarVehiculo, "Error de conexión: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
    }

    // Implementa esta función para generar IDs únicos
    private fun generarNuevoId(): Long {
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
}