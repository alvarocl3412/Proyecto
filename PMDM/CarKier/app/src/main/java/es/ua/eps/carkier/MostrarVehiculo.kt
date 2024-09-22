package es.ua.eps.carkier

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.ua.eps.carkier.Modelos.Vehiculos
import es.ua.eps.carkier.Retrofit.RetrofitClient
import es.ua.eps.carkier.databinding.ActivityMostrarVehiculoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse

class MostrarVehiculo : AppCompatActivity() {
    private lateinit var binding: ActivityMostrarVehiculoBinding
    private var vehiculo: Vehiculos? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarVehiculoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener los datos del vehículo pasados por intent
        val vehiculoId: Long? = intent.getLongExtra("idvehiculo", -1L)
        // Llamar a la función en un CoroutineScope
        if (vehiculoId != null) {
            CoroutineScope(Dispatchers.Main).launch {
                vehiculo = obtenerVehiculoPorId(vehiculoId)
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

    fun mostrarDetallesVehiculo(vehiculos: Vehiculos?) {
        if (vehiculos?.idEmpresa == null){
            binding.txtMostrarPertenece.text="Pertenece al usuario: ${vehiculos?.idUsuariosPropietario}"
        } else {
            binding.txtMostrarPertenece.text="Pertenece a la empresa: ${vehiculos?.idEmpresa}"
        }
        binding.txtMostrarEstado.text ="Estado: ${vehiculos?.idEstado}"
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
                val bitmap = binding.imgVehiucloMostrar.setBase64Image(vehiculo!!.imagen.toString())
                if (bitmap != null) {
                    binding.imgVehiucloMostrar.setScaledImage(bitmap)
                } else {
                    Log.e("MostrarVehiculo", "El bitmap es nulo")
                }
            }
        }

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