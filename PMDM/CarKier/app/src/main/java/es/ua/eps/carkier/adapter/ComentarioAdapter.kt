package es.ua.eps.carkier.adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.ua.eps.carkier.R
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.recyclerview.widget.LinearLayoutManager
import es.ua.eps.carkier.Modelos.Comentario
import es.ua.eps.carkier.Modelos.Usuarios
import es.ua.eps.carkier.Retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class ComentarioAdapter(
    private val context: Context,
    private val comentarios: MutableList<Comentario>, // Lista mutable de comentarios
    private val responderCallback: (Comentario, String) -> Unit // Callback para enviar respuesta
) : RecyclerView.Adapter<ComentarioAdapter.ComentarioViewHolder>() {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("usuario", Context.MODE_PRIVATE)

    // ViewHolder que contiene las vistas para cada tarjeta
    class ComentarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtComenUsuario: TextView = itemView.findViewById(R.id.txtComentarioUsuario)
        val txtFecha: TextView = itemView.findViewById(R.id.txtComentarioFecha)
        val txtdescripcion: TextView = itemView.findViewById(R.id.txtDescripcion)
        val txtVerRespuestas: TextView = itemView.findViewById(R.id.txtVerRespuestas)
        val txtResponder: TextView = itemView.findViewById(R.id.txtResponder)
        val recyclerRespuestas: RecyclerView = itemView.findViewById(R.id.recyclerRespuestas)
        val layoutResponder: LinearLayout = itemView.findViewById(R.id.layoutResponder)
        val etResponder: EditText = itemView.findViewById(R.id.etResponder)
        val btnCancelarRespuesta: Button = itemView.findViewById(R.id.btnCancelarRespuesta)
        val btnEnviarRespuesta: Button = itemView.findViewById(R.id.btnEnviarRespuesta)
    }

    // Infla el layout de cada elemento (cardview_carnet.xml)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentarioViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_comentario, parent, false)
        return ComentarioViewHolder(view)
    }

    // Asigna los datos a cada tarjeta (elemento) del RecyclerView
    override fun onBindViewHolder(holder: ComentarioViewHolder, position: Int) {
        val comen = comentarios[position]

        // Asignamos los valores a los campos correspondientes
        UsuarioNombre(comen.idUsuario) { nombreUsuario ->
            if (nombreUsuario != null) {
                // Usa el nombre del usuario aquí
                holder.txtComenUsuario.text = nombreUsuario
            } else {
                // Maneja el caso en que no se pudo obtener el nombre
                holder.txtComenUsuario.text = "Nombre no disponible"
            }
        }

        // Define el formato original de la fecha (año-mes-día)
        val formatoEntrada = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        // Define el formato de salida (día/mes/año)
        val formatoSalida = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        // Convierte la fecha de entrada a un objeto Date y luego formatea a salida
        val fecha: Date? = formatoEntrada.parse(comen.fecha)
        if (fecha != null) {
            holder.txtFecha.text = formatoSalida.format(fecha)
        } else {
            // Devuelve la fecha original si hubo un error
            holder.txtFecha.text = comen.fecha
        }

        holder.txtdescripcion.text = comen.comentario

        val respuestas = comentarios.filter { it.idComentarioRespuesta == comen.id }

        holder.txtVerRespuestas.text = if (respuestas.isNotEmpty()) {
            "Ver respuestas (${respuestas.size})"
        } else {
            "Sin respuestas"
        }
        // Manejar el botón "Ver respuestas"
        holder.txtVerRespuestas.visibility =
            if (respuestas.isNotEmpty()) View.VISIBLE else View.GONE
        holder.txtVerRespuestas.setOnClickListener {
            if (holder.recyclerRespuestas.visibility == View.GONE) {
                holder.recyclerRespuestas.visibility = View.VISIBLE
                holder.txtVerRespuestas.text = "Ocultar respuestas"
                mostrarRespuestas(holder.recyclerRespuestas, respuestas)
            } else {
                holder.recyclerRespuestas.visibility = View.GONE
                holder.txtVerRespuestas.text = "Ver respuestas (${respuestas.size})"
            }
        }

        // Manejar el botón "Responder"
        holder.txtResponder.setOnClickListener {
            holder.layoutResponder.visibility = View.VISIBLE

            holder.btnEnviarRespuesta.setOnClickListener {
                val usuId = sharedPreferences.getLong("id", -1L)
                val respuestaTexto = holder.etResponder.text.toString()
                if (respuestaTexto.isNotEmpty()) {
                    // Crear la nueva respuesta
                    val nuevaRespuesta = comen?.id?.let { idComentario ->
                        Comentario(
                            idUsuario = usuId,  // Suponiendo que esto no es null, ya que verificamos antes
                            idVehiculo = comen.idVehiculo,  // Asegúrate de que este ID es correcto
                            idComentarioRespuesta = idComentario,  // ID del comentario al que se responde
                            comentario = respuestaTexto
                        )
                    }
                    if (nuevaRespuesta != null) {
                        // Llamar a la función para crear el comentario
                        crearComentario(nuevaRespuesta) { exito ->
                            if (exito) {
                                // Si se creó correctamente, agregar a la lista y notificar al adaptador
                                comentarios.add(nuevaRespuesta)
                                notifyItemInserted(comentarios.size - 1) // Notificar al adaptador
                                holder.layoutResponder.visibility =
                                    View.GONE // Ocultar el layout de respuesta
                                holder.etResponder.text.clear() // Limpiar el campo de texto
                            } else {
                                Toast.makeText(
                                    holder.itemView.context,
                                    "Error al crear la respuesta",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                } else {
                    Toast.makeText(
                        holder.itemView.context,
                        "Por favor escribe una respuesta",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        // Cancelar respuesta
        holder.btnCancelarRespuesta.setOnClickListener {
            holder.etResponder.text.clear()
            holder.layoutResponder.visibility = View.GONE
        }

        // Manejar el botón "Enviar" en la respuesta

    }

    // Devuelve el número de carnets en la lista
    override fun getItemCount(): Int {
        return comentarios.filter { it.idComentarioRespuesta == null }.size
    }

    // Cambia la función crearComentario para aceptar un callback
    fun crearComentario(comentario: Comentario, onComplete: (Boolean) -> Unit) {
        RetrofitClient.instance.ComentarioRegistrar(comentario).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    // Si la respuesta es exitosa, llamamos al callback con verdadero
                    onComplete(true)
                } else {
                    // Mensaje de error más detallado
                    val errorBody = response.errorBody()?.string()
                    Log.e("CrearComentarioError", "Error: $errorBody")
                    onComplete(false) // Llamamos al callback con falso en caso de error
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {

            }
        })
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

    private fun mostrarRespuestas(recyclerView: RecyclerView, respuestas: List<Comentario>) {
        recyclerView.adapter = RespuestaAdapter(respuestas)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    }

}