package br.com.cotemig.jose.matheus.moviesandactors.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.SerieInfo
import br.com.cotemig.jose.matheus.moviesandactors.services.RetrofitInitializer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_serie_info.*
import retrofit2.Call
import retrofit2.Response

class SerieInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serie_info)

        var id = intent.getIntExtra("id", 0)
        var season = intent.getIntExtra("season", 0)
        var id_episode = intent.getIntExtra("id_episode", 0)

        getSerieInfo(id, season, id_episode)
    }

    fun getSerieInfo(id: Int, season: Int, id_episode: Int){
        val s = RetrofitInitializer().serviceSeries()
        val call = s.getserieinfo(id,season,id_episode,"ec0d4e364d9d4899a085d61c47e589d3")

        call.enqueue(object : retrofit2.Callback<SerieInfo>{
            override fun onResponse(call: Call<SerieInfo>, response: Response<SerieInfo>) {
                response?.let{
                    if (it.code() == 200) {
                        name_serieinfo.text = it.body()!!.name
                        description.text = it.body()!!.overview

                        Glide.with(this@SerieInfoActivity)
                            .load("https://image.tmdb.org/t/p/w500".plus(it.body()!!.still_path))
                            .into(image_serieinfo)
                    }
                }
            }

            override fun onFailure(call: Call<SerieInfo>, t: Throwable) {
                //Toast.makeText(this@MovieInfoActivity, "Erro Movie Info", Toast.LENGTH_LONG).show()
            }
        })
    }
}