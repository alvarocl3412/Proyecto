package es.ua.eps.carkier

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import es.ua.eps.carkier.CrearCuenta.CrearCuenta
import es.ua.eps.carkier.CrearCuenta.OlvideContrasenia
import es.ua.eps.carkier.Modelos.Usuarios
import es.ua.eps.carkier.Retrofit.RetrofitClient
import es.ua.eps.carkier.databinding.ActivityInicioSesionBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InicioSesion : AppCompatActivity() {
    private lateinit var binding: ActivityInicioSesionBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa SharedPreferences para guardar datos persistentes
        sharedPreferences = getSharedPreferences("usuario", MODE_PRIVATE)

        // Verifica si el usuario ya está registrado y redirige si es necesario
        verificarUsuarioRegistrado()

        // Configura el botón de inicio de sesión
        binding.btnInicioSesion.setOnClickListener {
            val email = binding.txtEmail.text.toString()
            val contrasena = binding.txtContra.text.toString()
            comprobarExistente(email, contrasena)
        }

        // Configura el botón de crear cuenta
        binding.btnCrearCuenta.setOnClickListener {
            val intent = Intent(this, CrearCuenta::class.java)
            startActivity(intent)
        }

        // Configura el botón de "Olvidé mi contraseña"
        binding.txtOlvideContra.setOnClickListener {
            val intent = Intent(this, OlvideContrasenia::class.java)
            startActivity(intent)
        }
    }

    // Método que comprueba si el usuario existe en la API
    private fun comprobarExistente(email: String, contra: String) {
        RetrofitClient.instance.loginUsuario(email, contra).enqueue(object : Callback<Usuarios> {
            override fun onResponse(call: Call<Usuarios>, response: Response<Usuarios>) {
                if (response.isSuccessful) {
                    // Si la respuesta es exitosa, obtener el usuario y guardar datos
                    val usuario = response.body()
                    Toast.makeText(this@InicioSesion, "Bienvenido ${usuario?.nombre}", Toast.LENGTH_SHORT).show()

                    guardarDatosUsuario(usuario)

                    // Redirige a la actividad principal
                    val intent = Intent(this@InicioSesion, Principal::class.java)
                    startActivity(intent)

                    //Terminamos la actividad para que no regresa a ella
                    finish()
                } else {
                    // Maneja la respuesta no exitosa
                    Toast.makeText(this@InicioSesion, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Usuarios>, t: Throwable) {
                // Maneja errores de conexión
                Toast.makeText(this@InicioSesion, "Fallo: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // Método para guardar los detalles del usuario en SharedPreferences
    private fun guardarDatosUsuario(usuario: Usuarios?) {
        val editor = sharedPreferences.edit()
        // Guardar detalles del usuario
        editor.putBoolean("recordarme", true) // Ya no depende de un checkbox, siempre guardamos
        editor.putLong("id", usuario?.id ?: 0)
        editor.putString("dni", usuario?.dni)
        editor.putString("nombre", usuario?.nombre)
        editor.putString("apellido", usuario?.apellidos)
        editor.putString("telefono", usuario?.telefono)
        editor.putString("correo", usuario?.correo)
        editor.putString("contrasena", usuario?.contrasena)
        editor.putString("fechaNac", usuario?.fechaNacimiento.toString())
        editor.apply() // Aplica los cambios
    }

    // Verifica si el usuario ya está registrado
    private fun verificarUsuarioRegistrado() {
        // Si el usuario seleccionó 'recordarme', redirigir a la pantalla principal
        val recordarme = sharedPreferences.getBoolean("recordarme", false)
        if (recordarme) {
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
            finish() // Termina la actividad para que no pueda regresar a ella
        }
    }
}
