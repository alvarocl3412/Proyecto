package es.ua.eps.carkier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.ua.eps.carkier.databinding.ActivityInicioSesionBinding


class InicioSesion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityInicioSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnInicioSesion.setOnClickListener(){
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }

        binding.btnCrearCuenta.setOnClickListener(){
            val intent = Intent( this,CrearCuenta::class.java)
            startActivity(intent)
        }

        binding.txtOlvideContra.setOnClickListener(){
            val intent = Intent( this,OlvideContrasenia::class.java)
            startActivity(intent)
        }

    }
}