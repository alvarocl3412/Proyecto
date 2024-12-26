package es.ua.eps.carkier.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import es.ua.eps.carkier.Modelos.Contrato
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
        val imgContrato: ImageView = itemView.findViewById(R.id.imgVehiculo)
        val txtMatricula: TextView = itemView.findViewById(R.id.txtMatricula)
        val txtFechaInicio: TextView = itemView.findViewById(R.id.txtFechaInicio)
        val txtFechaFinal: TextView = itemView.findViewById(R.id.txtFechaFinal)
        val txtPrecioDia: TextView = itemView.findViewById(R.id.txtPrecioDia)
        val txtPrecioFinal: TextView = itemView.findViewById(R.id.txtPrecioFinal)
        val btnCancelar: Button = itemView.findViewById(R.id.btnCancelar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContratoViewHolder {
        // Inflar el layout del item
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_contrato, parent, false)
        return ContratoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContratoViewHolder, position: Int) {
        val contrato = contratos[position]

        // Configurar los datos en el ViewHolder
        // Llamada a la API para obtener la matrícula del vehículo
        obtenerMatricula(contrato.idvehiculo.toInt(), holder.txtMatricula)

        holder.txtFechaInicio.text = "Fecha Inicio: ${contrato.fechaInicio}"
        holder.txtFechaFinal.text = "Fecha Final: ${contrato.fechaFin}"
        holder.txtPrecioDia.text = "Precio por Día: ${contrato.precioDia}"
        holder.txtPrecioFinal.text = "Precio Final: ${contrato.precioTotal}"
        holder.imgContrato.setImageResource(R.drawable.contrato)



        // Configurar el botón de cancelar
        holder.btnCancelar.setOnClickListener {
            // Aquí puedes definir qué hacer cuando el botón de cancelar sea presionado
            // Por ejemplo, llamar a un método que marque el contrato como cancelado o algo similar
            Toast.makeText(holder.itemView.context, "Contrato cancelado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return contratos.size
    }

    // Función para obtener la matrícula del vehículo usando Retrofit
     fun obtenerMatricula(id: Int, txtMatricula: TextView) {
        // Llamada a la API usando Retrofit
        RetrofitClient.instance.VehiculoId(id.toLong()).enqueue(object : Callback<Vehiculos> {
            override fun onResponse(call: Call<Vehiculos>, response: Response<Vehiculos>) {
                if (response.isSuccessful) {
                    // Si la respuesta es exitosa, obtener el objeto Vehiculo
                    val vehiculo = response.body()
                    if (vehiculo != null) {
                        // Actualizamos el TextView en el hilo principal
                        txtMatricula.text = "Matrícula: ${vehiculo.matricula}"
                    } else {
                        // Si no hay datos, mostramos un mensaje
                        txtMatricula.text = "Vehículo no encontrado"
                    }
                } else {
                    // Manejo de respuesta no exitosa
                    Toast.makeText(
                        txtMatricula.context,
                        "Error al obtener el vehículo: ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Vehiculos>, t: Throwable) {
                // Manejo de errores de conexión
                Toast.makeText(
                    txtMatricula.context,
                    "Fallo al conectar: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
