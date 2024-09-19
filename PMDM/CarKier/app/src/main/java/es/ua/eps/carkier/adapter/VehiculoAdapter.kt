package es.ua.eps.carkier.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.ua.eps.carkier.Modelos.Vehiculos
import es.ua.eps.carkier.R
class VehiculoAdapter(private val vehiculos: List<Vehiculos>) :
    RecyclerView.Adapter<VehiculoAdapter.VehiculoViewHolder>() {

    class VehiculoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtMarca: TextView = itemView.findViewById(R.id.txtMarca)
        val txtModelo: TextView = itemView.findViewById(R.id.txtModelo)
        val txtKm: TextView = itemView.findViewById(R.id.txtKm)
        val txtPrecioVenta: TextView = itemView.findViewById(R.id.txtPrecioVenta)
        val txtPrecioDia: TextView = itemView.findViewById(R.id.txtPrecioDia)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiculoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vehiculo, parent, false)
        return VehiculoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VehiculoViewHolder, position: Int) {
        val vehiculo = vehiculos[position]


        holder.txtMarca.text = "Marca: " + vehiculo.marca
        holder.txtModelo.text = "Modelo: " + vehiculo.modelo
        holder.txtKm.text = "Kilometros: " + vehiculo.km.toString()
        holder.txtPrecioVenta.text = "Precio: " + vehiculo.precioventa.toString()
        holder.txtPrecioDia.text = "Precio/Día: " + vehiculo.preciodia.toString()
    }

    override fun getItemCount() = vehiculos.size
}
