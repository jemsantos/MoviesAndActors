package br.com.cotemig.jose.matheus.moviesandactors.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.Atores
import br.com.cotemig.jose.matheus.moviesandactors.models.ListAtores
import br.com.cotemig.jose.matheus.moviesandactors.models.Movie
import br.com.cotemig.jose.matheus.moviesandactors.services.RetrofitInitializer
import br.com.cotemig.jose.matheus.moviesandactors.ui.adapters.AtoresAdapter
import br.com.cotemig.jose.matheus.moviesandactors.ui.adapters.MoviesAdapter
import retrofit2.Call
import retrofit2.Response

class AtoresActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atores)

        getAtores()
    }

    fun getAtores(){
        // inicializando o retrofit
        var r = RetrofitInitializer().serviceAtores()

        // criando o objeto "chamador" dos metodos
        var call = r.getatores("ec0d4e364d9d4899a085d61c47e589d3")

        //chamada assincrona
        call.enqueue(object: retrofit2.Callback<ListAtores>
        {
            //falha requisição
            override fun onFailure(call: Call<ListAtores>?, t: Throwable?) {
                //Toast.makeText(this@AtoresActivity, "OPS", Toast.LENGTH_LONG).show()
            }

            //sucesso  requisição
            override fun onResponse(call: Call<ListAtores>?, response: Response<ListAtores>?) {

                //Toast.makeText(this@AtoresActivity, "ok", Toast.LENGTH_LONG).show()
                response?.let{
                    if(it.code()==200)
                    {
                        it.body()?.let { it2 ->
                            it2.results?.let{ list ->
                                showAtores(list)
                            }
                        }
                    }
                }
            }
        })
    }

    fun showAtores(list: List<Atores>){
        var atores = findViewById<RecyclerView>(R.id.listaatores)

        atores.adapter = AtoresAdapter(this,list){ ator ->
            var intent = Intent(this, AtorMovieActivity::class.java)
            intent.putExtra("id", ator.id)
            startActivity(intent)
        }

        atores.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

}