package es.ua.eps.carkier.CrearCuenta

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.view.animation.AnimationUtils
import es.ua.eps.carkier.R
import es.ua.eps.carkier.databinding.ActivityCrearCuentaBinding

class CrearCuenta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCrearCuentaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSiguiente.setOnClickListener(){
            val intent = Intent(this, CrearCuenta2::class.java)
            startActivity(intent)
        }

    }

}