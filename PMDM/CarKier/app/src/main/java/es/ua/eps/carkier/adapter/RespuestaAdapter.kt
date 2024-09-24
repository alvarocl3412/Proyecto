package es.ua.eps.carkier.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.ua.eps.carkier.Modelos.Comentario
import es.ua.eps.carkier.Modelos.Usuarios
import es.ua.eps.carkier.R
import es.ua.eps.carkier.Retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class RespuestaAdapter(private val respuestas: List<Comentario>) :
    RecyclerView.Adapter<RespuestaAdapter.RespuestaViewHolder>() {

    inner class RespuestaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtUsu: TextView = itemView.findViewById(R.id.txtRespUsuario)
        val txtFecha : TextView = itemView.findViewById(R.id.txtRespFecha)
        val txtRespuesta: TextView = itemView.findViewById(R.id.txtResDescripcion)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RespuestaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_respuesta, parent, false)
        return RespuestaViewHolder(view)
    }

    override fun onBindViewHolder(holder: RespuestaViewHolder, position: Int) {
        val respuesta = respuestas[position]

        UsuarioNombre(respuesta.idUsuario) { nombreUsuario ->
            if (nombreUsuario != null) {
                // Usa el nombre del usuario aquí
                holder.txtUsu.text = nombreUsuario
            } else {
                // Maneja el caso en que no se pudo obtener el nombre
                holder.txtUsu.text = "Nombre no disponible"
            }
        }

        // Define el formato original de la fecha (año-mes-día)
        val formatoEntrada = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        // Define el formato de salida (día/mes/año)
        val formatoSalida = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        // Convierte la fecha de entrada a un objeto Date y luego formatea a salida
        val fecha: Date? = formatoEntrada.parse(respuesta.fecha)
        if (fecha != null) {
            holder.txtFecha.text = formatoSalida.format(fecha)
        } else {
            // Devuelve la fecha original si hubo un error
            holder.txtFecha.text = respuesta.fecha
        }
        holder.txtRespuesta.text = respuesta.comentario

    }

    override fun getItemCount(): Int {
        return respuestas.size
    }

    fun UsuarioNombre(id: Long, callback: (String?) -> Unit) {
        RetrofitClient.instance.UsuarioId(id).enqueue(object : Callback<Usuarios> {
            override fun onResponse(call: Call<Usuarios>, response: Response<Usuarios>) {
                if (response.isSuccessful) {
                    // Si la respuesta es exitosa, obtener el usuario
                    val usuario = response.body()
                    if (usuario != null) {

                        callback(usuario.nombre) // Retorna el nombre del usuario a través del callback
                    } else {
                        callback(null) // Maneja el caso de un cuerpo de respuesta nulo
                    }
                } else {
                    callback(null) // Maneja respuestas no exitosas
                }
            }

            override fun onFailure(call: Call<Usuarios>, t: Throwable) {
                callback(null) // Maneja el fallo de la petición
            }
        })
    }
}

