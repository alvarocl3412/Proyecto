package es.ua.eps.carkier.Modelos

data class Usuarios(
    val id: Long,
    val dni: String,
    var nombre: String,
    var apellidos: String,
    var telefono: String,
    var correo: String,
    var contrasena: String,
    var fechaNacimiento: String
)
