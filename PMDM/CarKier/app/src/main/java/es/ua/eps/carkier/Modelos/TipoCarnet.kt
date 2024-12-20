package es.ua.eps.carkier.Modelos

data class TipoCarnet(
    val id: Int,
    val nombre: String
) {
    override fun toString(): String {
        return nombre  // Solo queremos mostrar el nombre en el Spinner
    }
}