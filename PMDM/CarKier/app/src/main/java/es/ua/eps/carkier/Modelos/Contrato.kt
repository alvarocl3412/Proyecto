package es.ua.eps.carkier.Modelos

data class Contrato(
    val id: Long,
    var idvehiculo: Long,
    var idCliente: Long,
    var idEstado: Long,
    val idSeguro: Long,
    val precioDia: Long,
    val precioTotal: Long,
    val fechaInicio: String,
    val fechaFin: String,
) {
    constructor(
        idvehiculo: Long,
        idCliente: Long,
        idSeguro: String,
        precioDia: Long,
        precioTotal: Long,
        fechaInicio: String,
        fechaFin: String
    ) :
            this(
                id = 0,
                idvehiculo = idvehiculo,
                idCliente = idCliente,
                idEstado = 1,
                idSeguro = idSeguro.toLong(),
                precioDia = precioDia,
                precioTotal = precioTotal,
                fechaInicio = fechaInicio,
                fechaFin = fechaFin
            )
}