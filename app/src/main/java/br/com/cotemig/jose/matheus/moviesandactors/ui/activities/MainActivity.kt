package br.com.cotemig.jose.matheus.moviesandactors.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.Account

// TODO
// Lista de filmes
// Lista de pessoas (atores)

// https://developers.themoviedb.org/3/getting-started/introduction

/*
Get Top Rated
GET
/movie/top_rated

Get Details
GET
/person/{person_id}

Movie Discover
GET
/discover/movie

Search Movies
GET
/search/movie
*/

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* pegar o conteúdo que veio da outra Activity */
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

        var buttonAtores = findViewById<Button>(R.id.buttonAtores)
        buttonAtores.setOnClickListener {
            telaAtores()
        }

        var buttonAtoresPopulares = findViewById<Button>(R.id.buttonAtoresPopulares)
        buttonAtoresPopulares.setOnClickListener {
            telaAtoresPopulares()
        }
    }

    fun telaMovies() {
        var intent = Intent(this, MoviesActivity::class.java)
        startActivity(intent)
    }

    fun telaSeries() {
        var intent = Intent(this, SeriesActivity::class.java)
        startActivity(intent)
    }

    fun telaAtores() {
        var intent = Intent(this, AtoresActivity::class.java)
        startActivity(intent)
    }

    fun telaAtoresPopulares() {
        var intent = Intent(this, PersonPopularActivity::class.java)
        startActivity(intent)
    }

}