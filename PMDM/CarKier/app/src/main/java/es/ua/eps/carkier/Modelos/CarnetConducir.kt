package es.ua.eps.carkier.Modelos


data class CarnetConducir (
    var id: Long? ,
    var idusuario: Long,
    var idTipocarnet: Long,
    var fechaExpedicion: String,
    var fechaCaducidad: String?,
) {
    constructor(idusuario: Long, idTipocarnet: Long, fechaExpedicion: String) : this(
        id = null, // Puedes dejar esto como null si no se proporciona
        idusuario = idusuario,
        idTipocarnet = idTipocarnet,
        fechaExpedicion = fechaExpedicion,
        fechaCaducidad = null // O puedes establecer un valor por defecto aquí
    )
}

