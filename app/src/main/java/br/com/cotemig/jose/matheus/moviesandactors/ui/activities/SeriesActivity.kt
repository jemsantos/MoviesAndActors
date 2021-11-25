package br.com.cotemig.jose.matheus.moviesandactors.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.ListSeries
import br.com.cotemig.jose.matheus.moviesandactors.models.Serie
import br.com.cotemig.jose.matheus.moviesandactors.services.RetrofitInitializer
import br.com.cotemig.jose.matheus.moviesandactors.ui.adapters.SeriesAdapter
import kotlinx.android.synthetic.main.activity_series.*

import retrofit2.Call
import retrofit2.Response

class SeriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_series)

        getSeries()
    }

    fun getSeries(){

        // inicializando o retrofit
        var s = RetrofitInitializer().serviceSeries()

        var call = s.getseries("ec0d4e364d9d4899a085d61c47e589d3")

        //chamada assincrona
        call.enqueue(object: retrofit2.Callback<ListSeries>
        {
            //falha requisição
            override fun onFailure(call: Call<ListSeries>?, t: Throwable?) {
                //Toast.makeText(this@SeriesActivity, "OPS", Toast.LENGTH_LONG).show()
            }

            //sucesso  requisição
            override fun onResponse(call: Call<ListSeries>?, response: Response<ListSeries>?) {
                //Toast.makeText(this@SeriesActivity, "ok", Toast.LENGTH_LONG).show()
                response?.let{
                    if (it.code() == 200) {
                        it.body()?.let { it2 ->
                            it2.results?.let{ list ->
                                showSeries(list)
                            }
                        }
                    }
                }
            }
        })
    }

    fun showSeries(list: List<Serie>) {
        var series = findViewById<RecyclerView>(R.id.listaseries)

        series.adapter = SeriesAdapter(this, list) { serie ->
            var intent = Intent(this, SeasonActivity::class.java)
            intent.putExtra("id", serie.id)
            startActivity(intent)
        }

        series.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}