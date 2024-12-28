package es.ua.eps.carkier.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import es.ua.eps.carkier.Modelos.Contrato
import es.ua.eps.carkier.Modelos.EstadoContrato
import es.ua.eps.carkier.Modelos.Vehiculos
import es.ua.eps.carkier.R
import es.ua.eps.carkier.Retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ContratoAdapter(
    private val contratos: List<Contrato>
) : RecyclerView.Adapter<ContratoAdapter.ContratoViewHolder>() {

    // Crear un ViewHolder que representa un item de la lista
    class ContratoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.cvContrato)
        val imgContrato: ImageView = itemView.findViewById(R.id.imgVehiculo)
        val txtMatricula: TextView = itemView.findViewById(R.id.txtMatricula)
        val txtFechaInicio: TextView = itemView.findViewById(R.id.txtFechaInicio)
        val txtFechaFinal: TextView = itemView.findViewById(R.id.txtFechaFinal)
        val txtPrecioDia: TextView = itemView.findViewById(R.id.txtPrecioDia)
        val txtPrecioFinal: TextView = itemView.findViewById(R.id.txtPrecioFinal)
        val btnCancelar: Button = itemView.findViewById(R.id.btnCancelar)
        val btnEstado: Button = itemView.findViewById(R.id.btnEstado)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContratoViewHolder {
        // Inflar el layout del item
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_contrato, parent, false)
        return ContratoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContratoViewHolder, position: Int) {
        val contrato = contratos[position]

        // Configurar los datos en el ViewHolder
        obtenerMatricula(contrato.idvehiculo.toInt(), holder.txtMatricula)
        obtenerEstado(contrato.idEstado.toInt()) { estado ->
            holder.btnEstado.text = estado
            actualizarColorCard(holder, estado)

            // Mostrar/ocultar botón "Cancelar" según el estado
            if (estado == "Planificado") {
                holder.btnCancelar.visibility = View.VISIBLE
            } else {
                holder.btnCancelar.visibility = View.GONE
            }
        }

        holder.txtFechaInicio.text = "Fecha Inicio: ${contrato.fechaInicio}"
        holder.txtFechaFinal.text = "Fecha Final: ${contrato.fechaFin}"
        holder.txtPrecioDia.text = "Precio por Día: ${contrato.precioDia}"
        holder.txtPrecioFinal.text = "Precio Final: ${contrato.precioTotal}"
        holder.imgContrato.setImageResource(R.drawable.contrato)

        // Configurar el botón de cancelar
        holder.btnCancelar.setOnClickListener {
            // Mostrar un cuadro de diálogo para confirmar la cancelación
            val context = holder.itemView.context
            androidx.appcompat.app.AlertDialog.Builder(context)
                .setTitle("Confirmación")
                .setMessage("¿Seguro que quieres cancelar el contrato?")
                .setPositiveButton("Sí") { dialog, _ ->
                    // Aquí puedes manejar la lógica para cancelar el contrato
                    cancelarContrato(contrato.id) { success ->
                        if (success) {
                            Toast.makeText(context, "Contrato cancelado exitosamente", Toast.LENGTH_SHORT).show()
                            // Opcionalmente, actualiza la lista o el estado del contrato
                        } else {
                            Toast.makeText(context, "Error al cancelar el contrato", Toast.LENGTH_SHORT).show()
                        }
                    }
                    dialog.dismiss()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }
    }

    override fun getItemCount(): Int {
        return contratos.size
    }

    // Función para obtener la matrícula del vehículo usando Retrofit
    private fun obtenerMatricula(id: Int, txtMatricula: TextView) {
        RetrofitClient.instance.VehiculoId(id.toLong()).enqueue(object : Callback<Vehiculos> {
            override fun onResponse(call: Call<Vehiculos>, response: Response<Vehiculos>) {
                if (response.isSuccessful) {
                    val vehiculo = response.body()
                    txtMatricula.text = vehiculo?.let { "Matrícula: ${it.matricula}" } ?: "Vehículo no encontrado"
                } else {
                    txtMatricula.text = "Error al obtener matrícula"
                }
            }

            override fun onFailure(call: Call<Vehiculos>, t: Throwable) {
                txtMatricula.text = "Error de conexión"
            }
        })
    }

    // Función para obtener el estado del contrato
    private fun obtenerEstado(id: Int, callback: (String) -> Unit) {
        RetrofitClient.instance.contratoEstado(id).enqueue(object : Callback<EstadoContrato> {
            override fun onResponse(call: Call<EstadoContrato>, response: Response<EstadoContrato>) {
                if (response.isSuccessful) {
                    val estado = response.body()?.estado ?: "Estado desconocido"
                    callback(estado) // Pasamos el estado al callback
                } else {
                    callback("Error al obtener estado")
                }
            }

            override fun onFailure(call: Call<EstadoContrato>, t: Throwable) {
                callback("Error de conexión")
            }
        })
    }

    // Actualizar el color del CardView según el estado
    private fun actualizarColorCard(holder: ContratoViewHolder, estado: String) {
        val colorRes = when (estado) {
            "Iniciado" -> R.drawable.borde_color_iniciado
            "Planificado" -> R.drawable.borde_color_planificado
            "Finalizado" -> R.drawable.borde_color_terminado
            else -> R.drawable.borde_color_iniciado
        }
        holder.cardView.setBackgroundResource(colorRes)
    }

    fun cancelarContrato(idContrato: Long, callback: (Boolean) -> Unit) {
        // Usamos enqueue para hacer la llamada asíncrona
        RetrofitClient.instance.cancelarContrato(idContrato.toInt()).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Si la respuesta fue exitosa (código 200-299), llamamos al callback con true
                    callback(true)
                } else {
                    // Si la respuesta no fue exitosa, llamamos al callback con false
                    callback(false)
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Si hubo un error en la llamada (por ejemplo, error de red), llamamos al callback con false
                callback(false)
            }
        })
    }

}

