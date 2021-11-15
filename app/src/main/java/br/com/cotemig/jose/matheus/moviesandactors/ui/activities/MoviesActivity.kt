package br.com.cotemig.jose.matheus.moviesandactors.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.ListMovies
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

        // criando o objeto "chamador" dos metodos
        //var call = s.getseries("/500?","ec0d4e364d9d4899a085d61c47e589d3")

        var call = r.getmovies("ec0d4e364d9d4899a085d61c47e589d3")

        //chamada assincrona
        call.enqueue(object: retrofit2.Callback<ListMovies>
        {
            //falha requisição
            override fun onFailure(call: Call<ListMovies>?, t: Throwable?) {
                Toast.makeText(this@MoviesActivity, "OPS", Toast.LENGTH_LONG).show()
            }

            //sucesso  requisição
            override fun onResponse(call: Call<ListMovies>?, response: Response<ListMovies>?) {

                Toast.makeText(this@MoviesActivity, "ok", Toast.LENGTH_LONG).show()
                /* response?.let{
                    if (it.code() == 200) {
                        it.adapter =
                            it.body()?.let { it2 ->
                                MoviesAdapter(this@MoviesActivity,
                                    it2.resultsMovie)
                            }

                    }
                } */
            }
        })

    }
}