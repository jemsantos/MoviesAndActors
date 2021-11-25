package br.com.cotemig.jose.matheus.moviesandactors.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.ListMovies
import br.com.cotemig.jose.matheus.moviesandactors.models.Movie
import br.com.cotemig.jose.matheus.moviesandactors.services.RetrofitInitializer
import br.com.cotemig.jose.matheus.moviesandactors.ui.adapters.MoviesAdapter
import retrofit2.Call
import retrofit2.Response

class MoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        getMovies()
    }

    fun getMovies(){

        // inicializando o retrofit
        var r = RetrofitInitializer().serviceMovies()

        var call = r.getmovies("ec0d4e364d9d4899a085d61c47e589d3")

        //chamada assincrona
        call.enqueue(object: retrofit2.Callback<ListMovies>
        {
            //falha requisição
            override fun onFailure(call: Call<ListMovies>?, t: Throwable?) {
                //Toast.makeText(this@MoviesActivity, "OPS", Toast.LENGTH_LONG).show()
            }

            //sucesso  requisição
            override fun onResponse(call: Call<ListMovies>?, response: Response<ListMovies>?) {
                //Toast.makeText(this@MoviesActivity, "ok", Toast.LENGTH_LONG).show()
                response?.let{
                    if (it.code() == 200) {
                        it.body()?.let { it2 ->
                            it2.results?.let{ list ->
                                showMovies(list)
                            }
                        }
                    }
                }
            }
        })
    }

    fun showMovies(list: List<Movie>) {
        var movies = findViewById<RecyclerView>(R.id.listamovies)

        movies.adapter = MoviesAdapter(this, list) { movie ->
            var intent = Intent(this, MovieInfoActivity::class.java)
            intent.putExtra("id", movie.id)
            startActivity(intent)
        }

        //MovieCastActivity

        movies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}