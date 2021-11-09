package br.com.cotemig.jose.matheus.moviesandactors.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import br.com.cotemig.jose.matheus.moviesandactors.R

class SplashActivity : AppCompatActivity() {
    // Timer da splash screen
    private val SPLASH_TIME_OUT = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable
        /*
         * Exibindo splash com um timer.
         */
        { // Esse método será executado sempre que o timer acabar
            // E inicia a activity principal
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)

            // Fecha esta activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}