package br.com.cotemig.jose.matheus.moviesandactors.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.AvailableRegions
import br.com.cotemig.jose.matheus.moviesandactors.models.Region
import br.com.cotemig.jose.matheus.moviesandactors.services.RetrofitInitializer
import br.com.cotemig.jose.matheus.moviesandactors.ui.adapters.RegionAdapter
import retrofit2.Call
import retrofit2.Response

class AvailableRegionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_available_region)

        getAvailableRegions()
    }

    fun getAvailableRegions() {
        val s = RetrofitInitializer().serviceAvailableRegion()
        var call = s.getRegions("ec0d4e364d9d4899a085d61c47e589d3")

        call.enqueue(object : retrofit2.Callback<AvailableRegions> {
            override fun onResponse(call: Call<AvailableRegions>, response: Response<AvailableRegions>) {
                if (response.code() == 200) {
                    response.body()?.let { availableRegions ->
                        availableRegions.results?.let { list ->
                            showRegions(list)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<AvailableRegions>, t: Throwable) {
                Toast.makeText(this@AvailableRegionActivity, "Oooopsss! Tivemos um erro", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun showRegions(list: List<Region>) {
        var regions = findViewById<RecyclerView>(R.id.regions)
        regions.adapter = RegionAdapter(this, list) { region ->
            // listar os provedores presente em um região
            var intent = Intent(this, ProviderStreamingActivity::class.java) // tela dos provedores
            intent.putExtra("watch_region", region.iso_3166_1) // codigo da regiao clicada
            startActivity(intent)
        }

        regions.layoutManager = LinearLayoutManager (this, LinearLayoutManager.VERTICAL, false)
    }

}