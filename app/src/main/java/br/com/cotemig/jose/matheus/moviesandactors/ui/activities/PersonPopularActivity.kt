package br.com.cotemig.jose.matheus.moviesandactors.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.Atores
import br.com.cotemig.jose.matheus.moviesandactors.models.Person
import br.com.cotemig.jose.matheus.moviesandactors.services.RetrofitInitializer
import br.com.cotemig.jose.matheus.moviesandactors.ui.adapters.ActorAdapter
import br.com.cotemig.jose.matheus.moviesandactors.ui.adapters.AtoresAdapter
import retrofit2.Call
import retrofit2.Response

class PersonPopularActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_popular)

        getPersonPopular()
    }

    fun getPersonPopular() {
        val s= RetrofitInitializer().servicePersonPopular()
        var call = s.getPersonPopular("ec0d4e364d9d4899a085d61c47e589d3")

        call.enqueue(object : retrofit2.Callback<Person> {
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                if (response.code() == 200) {

                    response.body()?.let { person ->
                        person.results?.let { list ->
                            showAtores(list)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                Toast.makeText(this@PersonPopularActivity, "Oooopsss! Tivemos um erro", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun showAtores(list: List<Atores>) {
        var atores = findViewById<RecyclerView>(R.id.actors)
        atores.adapter = ActorAdapter(this, list) { ator ->

            // listar os filmes em que o ator participou....
            // var intent = Intent(this, null) // tela dos times dentro do campeonato
            // intent.putExtra("knownFor", ator.known_for)
            // startActivity(intent)
        }

        atores.layoutManager = LinearLayoutManager (this, LinearLayoutManager.VERTICAL, false)
    }

}