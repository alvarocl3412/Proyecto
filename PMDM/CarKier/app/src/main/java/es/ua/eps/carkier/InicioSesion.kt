package es.ua.eps.carkier

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import es.ua.eps.carkier.CrearCuenta.CrearCuenta
import es.ua.eps.carkier.Modelos.Usuarios
import es.ua.eps.carkier.databinding.ActivityInicioSesionBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class InicioSesion : AppCompatActivity() {
    private lateinit var binding: ActivityInicioSesionBinding

    // para acceder a las preferencias compartidas,
    // que se usan para guardar datos persistentes en el dispositivo.
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInicioSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        Guardamos en MiPrefs  los datos y le ponemos mode_private
        para que solo la aplicacion sea accesible
         */
        sharedPreferences = getSharedPreferences("MiPrefs", MODE_PRIVATE)

        // Revisa si el usuario está registrado
        verificarUsuarioRegistrado()

        binding.btnInicioSesion.setOnClickListener {
            val email = binding.txtEmail.text.toString()
            val contrasena = binding.txtContra.text.toString()
            comprobarExistente(email, contrasena)
        }

        binding.btnCrearCuenta.setOnClickListener {
            val intent = Intent(this, CrearCuenta::class.java)
            startActivity(intent)
        }

        binding.txtOlvideContra.setOnClickListener {
            val intent = Intent(this, OlvideContrasenia::class.java)
            startActivity(intent)
        }
    }

    //Metodo que le pasamos el email y la contraseña
    private fun comprobarExistente(email: String, contra: String) {
        //Hacemos la llamada a la api
        RetrofitClient.instance.loginUsuario(email, contra).enqueue(object : Callback<Usuarios> {
            //Maneja la respuesta de la api
            override fun onResponse(call: Call<Usuarios>, response: Response<Usuarios>) {
                if (response.isSuccessful) {
                    // Si la respuesta es exitosa, mostrar el nombre del usuario
                    val usuario = response.body()
                    Toast.makeText(this@InicioSesion, "Usuario: ${usuario?.nombre}", Toast.LENGTH_SHORT).show()
                    Recordar(usuario)
                    // Navegar a la actividad Principal
                    val intent = Intent(this@InicioSesion, Principal::class.java)
                    startActivity(intent)
                    finish() // Termina esta actividad para que el usuario no regrese a ella
                } else {
                    // Si la respuesta no es exitosa, mostrar un mensaje de error
                    Toast.makeText(this@InicioSesion, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Usuarios>, t: Throwable) {
                // Si ocurre un error, mostrar el mensaje de fallo
                Toast.makeText(this@InicioSesion, "Fallo: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun Recordar(usuario: Usuarios?) {
        // Guardar el estado del checkbox y detalles del usuario
        val editor = sharedPreferences.edit()
        if (binding.chbRecordarContrasenia.isChecked) {
            editor.putBoolean("recordarme", true)
            // Guardar detalles del usuario
            editor.putLong("userId", usuario?.id ?: 0)
            editor.putString("userDni", usuario?.dni)
            editor.putString("userNombre", usuario?.nombre)
            editor.putString("userApellidos", usuario?.apellidos)
            editor.putString("userTelefono", usuario?.telefono)
            editor.putString("userCorreo", usuario?.correo)
            editor.putString("userContrasena", usuario?.contrasena)
            editor.putString("userFechaNacimiento", usuario?.fechaNacimiento)
        } else {
            editor.putBoolean("recordarme", false)
            // Borrar detalles del usuario si el checkbox no está marcado
            editor.remove("userId")
            editor.remove("userDni")
            editor.remove("userNombre")
            editor.remove("userApellidos")
            editor.remove("userTelefono")
            editor.remove("userCorreo")
            editor.remove("userContrasena")
            editor.remove("userFechaNacimiento")
        }
        editor.apply()
    }

    private fun verificarUsuarioRegistrado() {
        val recordarme = sharedPreferences.getBoolean("recordarme", false)
        if (recordarme) {
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
            finish() // Termina esta actividad para que el usuario no regrese a ella
        }
    }
}