package es.ua.eps.carkier.Modelos

import java.io.Serializable

data class DatosUsuarios (
    val id: Long,
    var puntos: Int?,
    var administrador: Boolean?,
    var fechaBanInicio: String,
    var fechaBanFinal: String,
    var cantidadBan: Int?,
    var marcaEliminar: Boolean
) : Serializable {

    // Secondary constructor that only takes id and puntos
    constructor(id: Long, puntos: Int?) : this(id, puntos, null, "", "", null, false)
}
