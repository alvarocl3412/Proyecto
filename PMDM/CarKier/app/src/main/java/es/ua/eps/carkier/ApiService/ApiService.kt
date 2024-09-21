package es.ua.eps.carkier.ApiService

import es.ua.eps.carkier.Modelos.CarnetConducir
import es.ua.eps.carkier.Modelos.TipoCarnet
import es.ua.eps.carkier.Modelos.Usuarios
import es.ua.eps.carkier.Modelos.Vehiculos
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    //Metodos para el usuario
    @POST("UsuarioInicioSesion")
    fun loginUsuario(@Query("correo") correo: String, @Query("contrasena") contrasena: String
    ): Call<Usuarios>

    @POST("UsuarioRegistrar")
    fun crearUsuario(@Body usuario: Usuarios):Call<String>

    //Metodos para el vehiculo
    @GET("VehiculosfindAll")
    fun mostrarVehiculos():Call<List<Vehiculos>>


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


}