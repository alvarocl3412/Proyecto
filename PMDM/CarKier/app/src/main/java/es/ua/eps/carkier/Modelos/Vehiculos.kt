package es.ua.eps.carkier.Modelos

data class Vehiculos(
    val  id: Long,
    var  idEmpresa: Int?,
    var  idUsuariosPropietario: Int?,
    var  idEstado: Int,
    var  matricula: String,
    var  marca: String,
    var  modelo: String,
    var  anio: Int,
    var  km: Int,
    var  precioventa: Double,
    var  preciodia: Double,
    val  imagen: String?
)
