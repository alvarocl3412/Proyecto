package es.ua.eps.carkier

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import es.ua.eps.carkier.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val uri: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.intro)
        binding.IntroVideo.setVideoURI(uri)
        binding.IntroVideo.start()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent( this,InicioSesion::class.java)
            startActivity(intent)
            finish()
        },5000)
    }
}