package br.com.cotemig.jose.matheus.moviesandactors.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.ListAtoresMovies
import br.com.cotemig.jose.matheus.moviesandactors.models.ListMovies
import br.com.cotemig.jose.matheus.moviesandactors.models.Movie
import br.com.cotemig.jose.matheus.moviesandactors.services.RetrofitInitializer
import br.com.cotemig.jose.matheus.moviesandactors.ui.adapters.MoviesAdapter
import retrofit2.Call
import retrofit2.Response

class AtorMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ator_movie)

        var id = intent.getIntExtra("id", 0)
        getMoviesAtor(id)
    }

    fun getMoviesAtor(id: Int) {

        // inicializando o retrofit
        var r = RetrofitInitializer().serviceAtores()

        // criando o objeto "chamador" dos metodos
        var call = r.getatoresmovies(id,"ec0d4e364d9d4899a085d61c47e589d3")

        //chamada assincrona
        call.enqueue(object: retrofit2.Callback<ListAtoresMovies>
        {
            //falha requisição
            override fun onFailure(call: Call<ListAtoresMovies>?, t: Throwable?) {
                //Toast.makeText(this@AtorMovieActivity, "OPS", Toast.LENGTH_LONG).show()
            }

            //sucesso  requisição
            override fun onResponse(call: Call<ListAtoresMovies>?, response: Response<ListAtoresMovies>?) {

                //Toast.makeText(this@AtorMovieActivity, "ok", Toast.LENGTH_LONG).show()
                response?.let{
                    if(it.code()==200)
                    {
                        it.body()?.let { it2 ->
                            it2.cast?.let{ list ->
                                showMoviesAtores(list)
                            }
                        }
                    }
                }
            }
        })

    }

    fun showMoviesAtores(list: List<Movie>){
        var movies = findViewById<RecyclerView>(R.id.listamoviesator)

        movies.adapter = MoviesAdapter(this,list) { movie ->
            var intent = Intent(this, MovieInfoActivity::class.java)
            intent.putExtra("id", movie.id)
            startActivity(intent)
        }

        movies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

}