package es.ua.eps.carkier.adapter

import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.ua.eps.carkier.Modelos.Vehiculos
import es.ua.eps.carkier.MostrarVehiculo
import es.ua.eps.carkier.R

class VehiculoAdapter(private val vehiculos: List<Vehiculos>) :
    RecyclerView.Adapter<VehiculoAdapter.VehiculoViewHolder>() {

    // ViewHolder que contiene las vistas para cada tarjeta
    class VehiculoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtMarca: TextView = itemView.findViewById(R.id.txtMarca)
        val txtModelo: TextView = itemView.findViewById(R.id.txtModelo)
        val txtKm: TextView = itemView.findViewById(R.id.txtKm)
        val txtPrecioVenta: TextView = itemView.findViewById(R.id.txtPrecioVenta)
        val txtPrecioDia: TextView = itemView.findViewById(R.id.txtPrecioDia)
        val imageView: ImageView = itemView.findViewById(R.id.imgVehiculo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiculoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vehiculo, parent, false)
        return VehiculoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VehiculoViewHolder, position: Int) {
        val vehiculo = vehiculos[position]

        // Asignar los valores a los campos correspondientes
        holder.txtMarca.text = "Marca: ${vehiculo.marca}"
        holder.txtModelo.text = "Modelo: ${vehiculo.modelo}"
        holder.txtKm.text = "Kilómetros: ${vehiculo.km}"
        holder.txtPrecioVenta.text =
            "Precio: ${vehiculo.precioventa?.toString() ?: "No se vende"}"
        holder.txtPrecioDia.text = "Precio/Día: ${vehiculo.preciodia}"

        // Decodificar la imagen si está disponible
        if (!vehiculo.imagen.isNullOrEmpty()) {
            holder.imageView.setBase64Image(vehiculo.imagen)
        } else {
            // Establecer una imagen predeterminada si no hay imagen
            holder.imageView.setImageResource(R.drawable.logo)
        }

        // Establecer el clic para cada ítem del RecyclerView
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, MostrarVehiculo::class.java)

            // Pasar el vehículo como Serializable para poder usarlo en la nueva actividad
            intent.putExtra("idvehiculo", vehiculo.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = vehiculos.size

    // Extensión para establecer la imagen en un ImageView desde una cadena Base64
    private fun ImageView.setBase64Image(base64Image: String) {
        try {
            // Decodificar la cadena Base64 directamente
            val imageBytes = Base64.decode(base64Image, Base64.DEFAULT)

            // Crear un Bitmap a partir de los bytes
            val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

            if (bitmap != null) {
                this.setImageBitmap(bitmap) // Asignar el Bitmap al ImageView
            } else {
                Log.e("ImageView", "No se pudo crear el bitmap desde los bytes")
                this.setImageResource(R.drawable.logo) // Imagen predeterminada
            }
        } catch (e: Exception) {
            Log.e("ImageView", "Error al convertir la cadena Base64 a Bitmap", e)
            this.setImageResource(R.drawable.logo) // Imagen predeterminada en caso de error
        }
    }
}
