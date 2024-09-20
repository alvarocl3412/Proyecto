package es.ua.eps.carkier.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.ua.eps.carkier.R
import android.widget.ImageView
import es.ua.eps.carkier.Modelos.CarnetConducir

class CarnetAdapter(private val carnets: List<CarnetConducir>) :
    RecyclerView.Adapter<CarnetAdapter.CarnetViewHolder>() {

    // ViewHolder que contiene las vistas para cada tarjeta
    class CarnetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtUsuario: TextView = itemView.findViewById(R.id.txtUsuariosCarnet)
        val txtTipo: TextView = itemView.findViewById(R.id.txtTipoCarnet)
        val txtFechaExpedicion: TextView = itemView.findViewById(R.id.txtCarnetFechaExpedicion)
        val txtFechaCaducidad: TextView = itemView.findViewById(R.id.txtCarnetFechaCaducided)
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
        holder.txtUsuario.text = "Usuario: ${carnet.idusuario}"
        holder.txtTipo.text = "Tipo: ${carnet.idTipocarnet}"
        holder.txtFechaExpedicion.text = "Fecha Expedición: ${carnet.fechaExpedicion}"
        holder.txtFechaCaducidad.text = "Fecha Caducidad: ${carnet.fechaCaducidad ?: "No disponible"}"

        // Aquí puedes asignar la imagen si tuvieras una diferente para cada carnet (por ejemplo)
        holder.imageView.setImageResource(R.drawable.carnet)
    }

    // Devuelve el número de carnets en la lista
    override fun getItemCount(): Int = carnets.size
}
