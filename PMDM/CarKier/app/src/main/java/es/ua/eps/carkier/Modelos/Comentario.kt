package es.ua.eps.carkier.Modelos

data class Comentario(
    val id: Long,
    var idUsuario: Long,
    var idVehiculo: Long,
    var idComentarioRespuesta: Long?,
    var comentario: String,
    var fecha: String? = null // Fecha opcional con valor por defecto
)
