package es.ua.eps.carkier.Modelos

data class Contrato(
    val id: Long,
    var idvehiculo: Long,
    var idCliente: Long,
    var idEstado: Long,
    val idSeguro: Long,
    val precioDia: Double,
    val precioTotal: Double,
    val fechaInicio: String,
    val fechaFin: String,
    val pagado: Boolean
) {
    constructor(
        idvehiculo: Long,
        idCliente: Long,
        idSeguro: String,
        precioDia: Double,
        precioTotal: Double,
        fechaInicio: String,
        fechaFin: String,
        pagado: Boolean
    ) :
            this(
                id = 0,
                idvehiculo = idvehiculo,
                idCliente = idCliente,
                idEstado = 4,
                idSeguro = idSeguro.toLong(),
                precioDia = precioDia,
                precioTotal = precioTotal,
                fechaInicio = fechaInicio,
                fechaFin = fechaFin,
                pagado = pagado
            )
}