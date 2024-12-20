package es.ua.eps.carkier.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.ua.eps.carkier.R
import android.widget.ImageView
import es.ua.eps.carkier.Modelos.CarnetConducir
import es.ua.eps.carkier.Modelos.TipoCarnet
import es.ua.eps.carkier.Retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class CarnetAdapter(
    private val carnets: List<CarnetConducir>,
    private val onClick: (CarnetConducir) -> Unit // Callback para manejar el clic
) : RecyclerView.Adapter<CarnetAdapter.CarnetViewHolder>() {

    // ViewHolder que contiene las vistas para cada tarjeta
    class CarnetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTipo: TextView = itemView.findViewById(R.id.txtTipoCarnet)
        val txtFechaExpedicion: TextView = itemView.findViewById(R.id.txtCarnetFechaExpedicion)
        val txtFechaCaducidad: TextView = itemView.findViewById(R.id.txtCarnetFechaCaducided)
        val txtActualizar: TextView = itemView.findViewById(R.id.txtActualizar)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    // Infla el layout de cada elemento (cardview_carnet.xml)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarnetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carnet, parent, false)
        return CarnetViewHolder(view)
    }

    // Asigna los datos a cada tarjeta (elemento) del RecyclerView
    override fun onBindViewHolder(holder: CarnetViewHolder, position: Int) {
        val carnet = carnets[position]

        // Asignamos los valores a los campos correspondientes
        holder.txtFechaExpedicion.text = "Fecha Expedición: ${carnet.fechaExpedicion}"
        holder.txtFechaCaducidad.text = "Fecha Caducidad: ${carnet.fechaCaducidad ?: "No disponible"}"

        // Llamada a la API para obtener el nombre del tipo de carnet
        verTipo(carnet.idTipocarnet.toString()) { tipoNombre ->
            holder.txtTipo.text = "Tipo: $tipoNombre"
        }

        // Parseamos la fecha de caducidad
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        try {
            val fechaCaducidad = carnet.fechaCaducidad?.let { dateFormat.parse(it) }
            val fechaActual = Date() // Fecha actual

            // Comparamos si la fecha de caducidad ha pasado
            if (fechaCaducidad != null && fechaCaducidad.before(fechaActual)) {
                // Si ha pasado, mostramos el TextView de actualización
                holder.txtActualizar.visibility = View.VISIBLE
                holder.txtActualizar.text = "Necesitas actualizar, está caducado"

                // Hacemos la animación para que esté parpadeando
                val blinkAnimation = AlphaAnimation(0.0f, 1.0f).apply {
                    duration = 2000
                    repeatMode = AlphaAnimation.REVERSE
                    repeatCount = AlphaAnimation.INFINITE
                }
                holder.txtActualizar.startAnimation(blinkAnimation)

            } else {
                // Si no ha pasado, ocultamos el TextView de actualización
                holder.txtActualizar.visibility = View.GONE
            }
        } catch (e: Exception) {
            e.printStackTrace()
            holder.txtActualizar.visibility = View.GONE
        }

        // Aquí puedes asignar la imagen si tuvieras una diferente para cada carnet
        holder.imageView.setImageResource(R.drawable.carnet)

        // Configurar el clic en el ítem
        holder.itemView.setOnClickListener {
            onClick(carnet) // Ejecutamos el callback con el objeto carnet
        }
    }

    // Devuelve el número de carnets en la lista
    override fun getItemCount(): Int = carnets.size

    // Función para hacer la llamada a la API y obtener el nombre del tipo de carnet
    fun verTipo(id: String, callback: (String) -> Unit) {
        RetrofitClient.instance.TipoCarnetNombre(id).enqueue(object : Callback<TipoCarnet> {
            override fun onResponse(call: Call<TipoCarnet>, response: Response<TipoCarnet>) {
                if (response.isSuccessful) {
                    val tipoNombre = response.body()?.nombre ?: "Tipo no disponible"
                    callback(tipoNombre)  // Llamamos al callback con el nombre del tipo
                } else {
                    callback("Error al obtener tipo")
                }
            }

            override fun onFailure(call: Call<TipoCarnet>, t: Throwable) {
                callback("Error en la petición: ${t.message}")
            }
        })
    }
}


