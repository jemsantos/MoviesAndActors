package br.com.cotemig.jose.matheus.moviesandactors.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.ListSeasons
import br.com.cotemig.jose.matheus.moviesandactors.models.Season
import br.com.cotemig.jose.matheus.moviesandactors.services.RetrofitInitializer
import br.com.cotemig.jose.matheus.moviesandactors.ui.adapters.SeasonsAdapter
import retrofit2.Call
import retrofit2.Response

class SeasonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_season)

        var id = intent.getIntExtra("id", 0)
        getSeasons(id)
    }

    fun getSeasons(id: Int) {

        // inicializando o retrofit
        var r = RetrofitInitializer().serviceSeries()

        // criando o objeto "chamador" dos metodos
        var call = r.getseriesseason(id,"ec0d4e364d9d4899a085d61c47e589d3")

        //chamada assincrona
        call.enqueue(object: retrofit2.Callback<ListSeasons>
        {
            //falha requisição
            override fun onFailure(call: Call<ListSeasons>?, t: Throwable?) {
                //Toast.makeText(this@SeasonActivity, "OPS", Toast.LENGTH_LONG).show()
            }

            //sucesso  requisição
            override fun onResponse(call: Call<ListSeasons>?, response: Response<ListSeasons>?) {

                //Toast.makeText(this@SeasonActivity, "ok", Toast.LENGTH_LONG).show()
                response?.let{
                    if(it.code()==200)
                    {
                        it.body()?.let { it2 ->
                            it2.seasons?.let{ list ->
                                showSeasons(list, id)
                            }
                        }
                    }
                }
            }
        })

    }

    fun showSeasons(list: List<Season>, id: Int){
        var seasons = findViewById<RecyclerView>(R.id.listaseasons)

        seasons.adapter = SeasonsAdapter(this,list) { season ->
            var intent = Intent(this, EpisodeActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("season", season.season_number)
            startActivity(intent)
        }

        seasons.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }


}