package br.com.cotemig.jose.matheus.moviesandactors.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.ListSeries
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

        // criando o objeto "chamador" dos metodos
        //var call = s.getseries("/500?","ec0d4e364d9d4899a085d61c47e589d3")

        var call = s.getseries("ec0d4e364d9d4899a085d61c47e589d3")

        //chamada assincrona
        call.enqueue(object: retrofit2.Callback<ListSeries>
        {
            //falha requisição
            override fun onFailure(call: Call<ListSeries>?, t: Throwable?) {
                Toast.makeText(this@SeriesActivity, "OPS", Toast.LENGTH_LONG).show()
            }

            //sucesso  requisição
            override fun onResponse(call: Call<ListSeries>?, response: Response<ListSeries>?) {

                Toast.makeText(this@SeriesActivity, "ok", Toast.LENGTH_LONG).show()
                response?.let{
                    if (it.code() == 200) {
                        listaseries.adapter =
                            it.body()?.let { it1 ->
                                SeriesAdapter(this@SeriesActivity, it1.results)
                            }
                    }
                }
            }
        })
    }
}