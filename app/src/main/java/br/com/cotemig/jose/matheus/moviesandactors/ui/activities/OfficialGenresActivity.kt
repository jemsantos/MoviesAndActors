package br.com.cotemig.jose.matheus.moviesandactors.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.AvailableRegions
import br.com.cotemig.jose.matheus.moviesandactors.models.Genre
import br.com.cotemig.jose.matheus.moviesandactors.models.OfficialGenres
import br.com.cotemig.jose.matheus.moviesandactors.models.Region
import br.com.cotemig.jose.matheus.moviesandactors.services.RetrofitInitializer
import br.com.cotemig.jose.matheus.moviesandactors.ui.adapters.GenreAdapter
import br.com.cotemig.jose.matheus.moviesandactors.ui.adapters.RegionAdapter
import retrofit2.Call
import retrofit2.Response

class OfficialGenresActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_official_genres)

        getOfficialGenres()
    }

    fun getOfficialGenres() {
        val s = RetrofitInitializer().serviceOfficialGenres()
        var call = s.getGenres("ec0d4e364d9d4899a085d61c47e589d3")

        call.enqueue(object : retrofit2.Callback<OfficialGenres> {
            override fun onResponse(call: Call<OfficialGenres>, response: Response<OfficialGenres>) {
                if (response.code() == 200) {
                    response.body()?.let { officialGenres ->
                        officialGenres.genres?.let { list ->
                            showGenres(list)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<OfficialGenres>, t: Throwable) {
                Toast.makeText(this@OfficialGenresActivity, "Oooopsss! Tivemos um erro", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun showGenres(list: List<Genre>) {
        var genres = findViewById<RecyclerView>(R.id.genres)
        genres.adapter = GenreAdapter(this, list) { genre ->
            // listar os provedores presente em um região
            //var intent = Intent(this, ProviderStreamingActivity::class.java) // tela dos provedores
            //intent.putExtra("watch_region", region.iso_3166_1) // codigo da regiao clicada
            //startActivity(intent)
        }

        genres.layoutManager = LinearLayoutManager (this, LinearLayoutManager.VERTICAL, false)
    }
}