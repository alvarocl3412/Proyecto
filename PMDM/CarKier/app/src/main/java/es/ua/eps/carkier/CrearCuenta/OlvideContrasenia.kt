package es.ua.eps.carkier.CrearCuenta

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.ua.eps.carkier.InicioSesion
import es.ua.eps.carkier.databinding.ActivityInicioSesionBinding
import es.ua.eps.carkier.databinding.ActivityOlvideContraseniaBinding
class OlvideContrasenia : AppCompatActivity() {
    private lateinit var binding: ActivityOlvideContraseniaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityOlvideContraseniaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEnviarInstrucciones.setOnClickListener {
            val correo = binding.etxtCorreoRecuperar.text.toString().trim()

            if (correo.isEmpty()) {
                Toast.makeText(this, "Debes introducir un correo", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:$correo")
                    putExtra(Intent.EXTRA_SUBJECT, "Recuperación de contraseña")
                    putExtra(Intent.EXTRA_TEXT, "Para recuperar tu contraseña, haz clic en el siguiente enlace o sigue las instrucciones proporcionadas...")
                }

                try {
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(this, "No se encontró una app de correo instalada", Toast.LENGTH_SHORT).show()
                }
            }
        }


        binding.btnVolverLogin.setOnClickListener {
            val intent = Intent(this, InicioSesion::class.java)
            startActivity(intent)
        }



    }
}