package es.ua.eps.carkier.ApiService

import es.ua.eps.carkier.Modelos.CarnetConducir
import es.ua.eps.carkier.Modelos.Comentario
import es.ua.eps.carkier.Modelos.EstadoVehiculo
import es.ua.eps.carkier.Modelos.TipoCarnet
import es.ua.eps.carkier.Modelos.Usuarios
import es.ua.eps.carkier.Modelos.Vehiculos
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.http.Body
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

    @PUT("updateVehiculo")
    fun updateVehiculo(@Body vehiculos: Vehiculos):Call<String>

    @GET("VehiuculosId/{id}")
    fun VehiculoId(@Path("id") id: Long?): Call<Vehiculos>

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

    @GET("mostrarCarnetsUsuario/{idusuario}")
    fun CarnetsPersona(@Path("idusuario") idusuario: Long): Call<List<CarnetConducir>>


    //Comentarios
    @GET("ComentarioidVehiculo/{idVehiculo}")
    fun ComentarioVehiculo(@Path("idVehiculo") id: Long): Call<MutableList<Comentario>>

    @POST("CrearComentario")
    fun ComentarioRegistrar(@Body comentario: Comentario):Call<String>



}