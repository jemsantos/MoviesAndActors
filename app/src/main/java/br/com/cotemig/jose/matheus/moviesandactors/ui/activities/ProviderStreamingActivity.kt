package br.com.cotemig.jose.matheus.moviesandactors.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.ProviderStreaming
import br.com.cotemig.jose.matheus.moviesandactors.models.Watch
import br.com.cotemig.jose.matheus.moviesandactors.services.RetrofitInitializer
import br.com.cotemig.jose.matheus.moviesandactors.ui.adapters.ProviderStreamingAdapter
import retrofit2.Call
import retrofit2.Response

class ProviderStreamingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider_streaming)

        var watchRegion = "BR"
        val bundle :Bundle ?=intent.extras
        if (bundle!=null){
            watchRegion = intent.getStringExtra("watch_region").toString()
        }

        getProviderStreaming(watchRegion)
    }

    fun getProviderStreaming(watchRegion: String) {

        val s = RetrofitInitializer().serviceProviderStreaming()

        Toast.makeText(this@ProviderStreamingActivity, watchRegion, Toast.LENGTH_LONG).show()

        var call = s.getProvidersStreamingByRegion(watchRegion, "ec0d4e364d9d4899a085d61c47e589d3")

        call.enqueue(object : retrofit2.Callback<Watch> {
            override fun onResponse(call: Call<Watch>, response: Response<Watch>) {
                if (response.code() == 200) {
                    response.body()?.let { watch ->
                        watch.results?.let { list ->
                            showProviders(list)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Watch>, t: Throwable) {
                Toast.makeText(this@ProviderStreamingActivity, "Oooopsss! Tivemos um erro", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun showProviders(list: List<ProviderStreaming>) {
        var providers = findViewById<RecyclerView>(R.id.providers)
        providers.adapter = ProviderStreamingAdapter(this, list) { provider ->

            // listar os filmes em que o ator participou....
            // var intent = Intent(this, null) // tela dos times dentro do campeonato
            // intent.putExtra("knownFor", ator.known_for)
            // startActivity(intent)
        }

        providers.layoutManager = LinearLayoutManager (this, LinearLayoutManager.VERTICAL, false)
    }

}