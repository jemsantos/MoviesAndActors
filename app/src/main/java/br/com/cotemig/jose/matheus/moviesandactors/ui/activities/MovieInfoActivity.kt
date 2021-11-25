package br.com.cotemig.jose.matheus.moviesandactors.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.MovieInfo
import br.com.cotemig.jose.matheus.moviesandactors.services.RetrofitInitializer
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Response
import android.widget.*
import kotlinx.android.synthetic.main.activity_movie_info.*

class MovieInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_info)

        var id = intent.getIntExtra("id", 0)
        getMovieInfo(id)
    }

    fun getMovieInfo(id: Int){
        val s = RetrofitInitializer().serviceMovies()
        val call = s.getmoviesinfo(id,"ec0d4e364d9d4899a085d61c47e589d3")

        call.enqueue(object : retrofit2.Callback<MovieInfo>{
            override fun onResponse(call: Call<MovieInfo>, response: Response<MovieInfo>) {
                response?.let{
                    if (it.code() == 200) {
                        name_movieinfo.text = it.body()!!.original_title
                        description.text = it.body()!!.overview

                        Glide.with(this@MovieInfoActivity)
                            .load("https://image.tmdb.org/t/p/w500".plus(it.body()!!.poster_path))
                            .into(image_movieinfo)
                    }
                }
            }

            override fun onFailure(call: Call<MovieInfo>, t: Throwable) {
                //Toast.makeText(this@MovieInfoActivity, "Erro Movie Info", Toast.LENGTH_LONG).show()
            }
        })
    }
}