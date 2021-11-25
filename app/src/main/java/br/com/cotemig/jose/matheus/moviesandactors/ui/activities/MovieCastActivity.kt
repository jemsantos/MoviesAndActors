package br.com.cotemig.jose.matheus.moviesandactors.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.Cast
import br.com.cotemig.jose.matheus.moviesandactors.models.ListCast
import br.com.cotemig.jose.matheus.moviesandactors.services.RetrofitInitializer
import br.com.cotemig.jose.matheus.moviesandactors.ui.adapters.CastAdapter

import retrofit2.Call
import retrofit2.Response

class MovieCastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cast)

        var id = intent.getIntExtra("id", 0)

        getCast(id)
    }

    fun getCast(id: Int){

        // inicializando o retrofit
        var r = RetrofitInitializer().serviceMovies()

        // criando o objeto "chamador" dos metodos
        var call = r.getcast(id,"ec0d4e364d9d4899a085d61c47e589d3")

        //chamada assincrona
        call.enqueue(object: retrofit2.Callback<ListCast>
        {
            //falha requisição
            override fun onFailure(call: Call<ListCast>?, t: Throwable?) {
                //Toast.makeText(this@MovieCastActivity, "OPS", Toast.LENGTH_LONG).show()
            }

            //sucesso  requisição
            override fun onResponse(call: Call<ListCast>?, response: Response<ListCast>?) {

                //Toast.makeText(this@MovieCastActivity, "ok CastActivity", Toast.LENGTH_LONG).show()
                response?.let{
                    if(it.code()==200)
                    {
                        it.body()?.let { it1 ->
                            it1.cast?.let{ list ->
                                showCast(list)
                            }
                        }
                    }
                }
            }
        })
    }

    fun showCast(list: List<Cast>){
        var cast = findViewById<RecyclerView>(R.id.listacast)

        cast.adapter = CastAdapter(this,list)

        cast.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

}