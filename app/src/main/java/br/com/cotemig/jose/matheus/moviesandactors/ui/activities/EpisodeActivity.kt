package br.com.cotemig.jose.matheus.moviesandactors.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.Episodes
import br.com.cotemig.jose.matheus.moviesandactors.models.ListEpisodes
import br.com.cotemig.jose.matheus.moviesandactors.services.RetrofitInitializer
import br.com.cotemig.jose.matheus.moviesandactors.ui.adapters.EpisodesAdapter
import retrofit2.Call
import retrofit2.Response

class EpisodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode)

        var id = intent.getIntExtra("id", 0)
        var season = intent.getIntExtra("season", 0)

        getEpisodes(id, season)
    }
    fun getEpisodes(id: Int, season: Int) {

        // inicializando o retrofit
        var r = RetrofitInitializer().serviceSeries()

        // criando o objeto "chamador" dos metodos
        var call = r.getseriesepisode(id,season,"ec0d4e364d9d4899a085d61c47e589d3")

        //chamada assincrona
        call.enqueue(object: retrofit2.Callback<ListEpisodes>
        {
            //falha requisição
            override fun onFailure(call: Call<ListEpisodes>?, t: Throwable?) {
                //Toast.makeText(this@EpisodeActivity, "OPS!!!!!", Toast.LENGTH_LONG).show()
            }

            //sucesso  requisição
            override fun onResponse(call: Call<ListEpisodes>?, response: Response<ListEpisodes>?) {

                //Toast.makeText(this@EpisodeActivity, "ok", Toast.LENGTH_LONG).show()
                response?.let{
                    if(it.code()==200)
                    {

                        it.body()?.let { it2 ->
                            it2.episodes?.let{ list ->
                                showEpisodes(list, id, season)
                            }
                        }
                    }
                }
            }
        })

    }

    fun showEpisodes(list: List<Episodes>, id: Int, season: Int){
        var episodes = findViewById<RecyclerView>(R.id.listaepisodes)

        episodes.adapter = EpisodesAdapter(this,list) { episode ->
            var intent = Intent(this, SerieInfoActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("season", season)
            intent.putExtra("id_episode", episode.episode_number)
            startActivity(intent)
        }

        episodes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

}