package es.ua.eps.carkier.Modelos

data class Comentario(
    val id: Long?,
    var idUsuario: Long,
    var idVehiculo: Long,
    var idComentarioRespuesta: Long?,
    var comentario: String,
    var fecha: String? = null // Fecha opcional con valor por defecto
) {
    constructor(idUsuario: Long, idVehiculo: Long, idComentarioRespuesta: Long, comentario: String) : this(
        id = null,
        idUsuario = idUsuario,
        idVehiculo = idVehiculo,
        idComentarioRespuesta = idComentarioRespuesta,
        comentario = comentario,
        fecha = null
    )
    constructor(idUsuario: Long, idVehiculo: Long, comentario: String) : this(
        id = null,
        idUsuario = idUsuario,
        idVehiculo = idVehiculo,
        idComentarioRespuesta = null,
        comentario = comentario,
        fecha = null
    )
}
