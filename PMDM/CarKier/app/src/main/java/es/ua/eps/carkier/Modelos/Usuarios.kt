package es.ua.eps.carkier.Modelos
import java.text.SimpleDateFormat
import java.util.*

data class Usuarios(
    var id: Long? = null,               // ID del usuario, puede ser null al crear un nuevo usuario
    var dni: String,                    // DNI del usuario
    var nombre: String,                 // Nombre del usuario
    var apellidos: String,              // Apellidos del usuario
    var telefono: String,               // Teléfono del usuario
    var correo: String,                 // Correo del usuario
    var contrasena: String,             // Contraseña del usuario
    var fechaNacimiento: String         // Fecha de nacimiento en formato "yyyy-MM-dd"
) {
    // Función para convertir una fecha a formato String (yyyy-MM-dd)
    companion object {
        fun convertirDateAString(fecha: Date): String {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            return dateFormat.format(fecha)
        }

        // Función para convertir un String a Date en formato (yyyy-MM-dd)
        fun convertirStringADate(fechaString: String): Date? {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            return try {
                dateFormat.parse(fechaString)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}
