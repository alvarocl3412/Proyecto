package es.ua.eps.carkier.ApiService

import es.ua.eps.carkier.Modelos.CarnetConducir
import es.ua.eps.carkier.Modelos.Comentario
import es.ua.eps.carkier.Modelos.Contrato
import es.ua.eps.carkier.Modelos.DatosUsuarios
import es.ua.eps.carkier.Modelos.EstadoContrato
import es.ua.eps.carkier.Modelos.EstadoVehiculo
import es.ua.eps.carkier.Modelos.FechaOcupada
import es.ua.eps.carkier.Modelos.Seguros
import es.ua.eps.carkier.Modelos.TipoCarnet
import es.ua.eps.carkier.Modelos.Usuarios
import es.ua.eps.carkier.Modelos.Vehiculos
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path


interface ApiService {

    //Metodos para el usuario
    @POST("UsuarioInicioSesion")
    fun loginUsuario(@Query("correo") correo: String, @Query("contrasena") contrasena: String
    ): Call<Usuarios>

    @POST("UsuarioRegistrar")
    fun crearUsuario(@Body usuario: Usuarios):Call<String>

    @GET("UsuarioId/{id}")
    fun UsuarioId(@Path("id") id: Long?):Call<Usuarios>

    //Metodos para el vehiculo
    @GET("estado/1")
    fun mostrarVehiculos():Call<List<Vehiculos>>

    @GET("VehiculosMarca/{marca}")
    fun filterVehculo(@Path("marca") marca: String):Call<List<Vehiculos>>

    @PUT("updateVehiculo")
    fun updateVehiculo(@Body vehiculos: Vehiculos):Call<String>

    @GET("VehiuculosId/{id}")
    fun VehiculoId(@Path("id") id: Long?): Call<Vehiculos>


    // Datos del usuario
    @GET("DatosUsuariosId/{id}")
    fun datosUsuarios(@Path("id") id: Long?): Call<DatosUsuarios>

    @PUT("updateDatosUsuarios")
    fun updateUsuario(@Body datosUsuarios: DatosUsuarios): Call<String>

    //Estado Vehiculo
    @GET("EstadoVehiculoId/{id}")
    fun EstadoVehiculoId(@Path("id") id:Long?): Call<EstadoVehiculo>

    //Metodos para el tipo carnet
    @GET("TipoCarnetfindAll")
    fun TiposCarnet(): Call<List<TipoCarnet>>

    @GET("TipoCarnetId/{id}")
    fun TipoCarnetNombre(@Path("id") id: String): Call<TipoCarnet>

    //Metodos para los carnets
    @POST("registrarCarnets")
    fun CrearCarnetUsuario(@Body carnet: CarnetConducir):Call<String>

    // Definir el método PUT para actualizar un carnet
    @PUT("updateCarnets")
    fun updateCarnet(@Body carnet: CarnetConducir): Call<CarnetConducir>


    @GET("mostrarCarnetsUsuario/{idusuario}")
    fun CarnetsPersona(@Path("idusuario") idusuario: Long): Call<List<CarnetConducir>>


    //Comentarios
    @GET("ComentarioidVehiculo/{idVehiculo}")
    fun ComentarioVehiculo(@Path("idVehiculo") id: Long): Call<MutableList<Comentario>>

    @POST("CrearComentario")
    fun ComentarioRegistrar(@Body comentario: Comentario):Call<String>

    //Seguros
    @GET("TipoSegurofindAll")
    fun Seguros():Call<List<Seguros>>

    // Contratos

    @GET("contratoEstadocliente/{idEstado}/{idCliente}")
    fun contratoEstado(@Path("idEstado") idEstado: Int, @Path("idCliente") idCliente: Int): Call<List<Contrato>>

    @GET("contratocliente/{idCliente}")
    fun contratosCliente(@Path("idCliente") idCliente: Int): Call<List<Contrato>>

    @GET("contratofechasOcupada/{idVehiculo}")
    fun getFechasOcupadas(@Path("idVehiculo") idVehiculo: Int): Call<FechaOcupada>

    @GET("EstadoContratoId/{id}")
    fun contratoEstado(@Path("id") id: Int): Call<EstadoContrato>

    @POST("CrearContrato")
    fun contratoCrear(@Body contrato: Contrato): Call<String>

    @DELETE("deleteContrato/{id}")
    fun cancelarContrato(@Path("id") id: Int): Call<Void>



}