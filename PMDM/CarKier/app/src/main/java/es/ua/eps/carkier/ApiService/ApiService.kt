package es.ua.eps.carkier.ApiService

import es.ua.eps.carkier.Modelos.Usuarios
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.Call


interface ApiService {

    @POST("UsuarioInicioSesion")
    fun loginUsuario(
        @Query("correo") correo: String,
        @Query("contrasena") contrasena: String
    ): Call<Usuarios>
}