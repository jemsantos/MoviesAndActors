package br.com.cotemig.jose.matheus.moviesandactors.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.Account

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var account = intent.getSerializableExtra("account") as Account

        // var welcome = findViewById<TextView>(R.id.welcome)
        // welcome.text = resources.getString(R.string.main_welcomemessage, account.name)

        var buttonSeries = findViewById<Button>(R.id.buttonSeries)
        buttonSeries.setOnClickListener {
            telaSeries()
        }

        var buttonMovies = findViewById<Button>(R.id.buttonMovies)
        buttonMovies.setOnClickListener {
            telaMovies()
        }

        /* var buttonAtores = findViewById<Button>(R.id.buttonAtores)
        buttonAtores.setOnClickListener {
            telaAtores()
        } */
    }

    fun telaSeries(){
        var intent = Intent(this, SeriesActivity::class.java)
        startActivity(intent)
    }

    fun telaMovies(){
        var intent = Intent(this, MoviesActivity::class.java)
        startActivity(intent)
    }
}