package es.ua.eps.carkier

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.ua.eps.carkier.databinding.ActivityOlvideContraseniaBinding

class OlvideContrasenia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityOlvideContraseniaBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}