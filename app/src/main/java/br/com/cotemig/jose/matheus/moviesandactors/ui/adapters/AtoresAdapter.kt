package br.com.cotemig.jose.matheus.moviesandactors.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.Atores
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest

class AtoresAdapter (var context: Context, var list: List<Atores>, var onClickMovie: (Atores) -> Unit) :
    RecyclerView.Adapter<AtoresAdapter.AtoresHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AtoresHolder {
        // carregando o xml na memória
        var view = LayoutInflater.from(context).inflate(R.layout.item_atores, parent, false)
        // retornando objeto holder instanciado, passando view no construtor
        return AtoresHolder(view)
    }

    override fun onBindViewHolder(holder: AtoresHolder, position: Int) {
        holder.bind(list[position], onClickMovie)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class AtoresHolder(var view: View) : RecyclerView.ViewHolder(view) {

        fun  bind(atores: Atores, onClickMovie: (Atores) -> Unit){

            var name_movie = view.findViewById<TextView>(R.id.name_ator)
            name_movie.text = atores.name

            var foto = view.findViewById<ImageView>(R.id.image_ator)

            foto.loadUrl("https://image.tmdb.org/t/p/w500"+atores.profile_path)

            var card_movies = view.findViewById<CardView>(R.id.card_atores)
            card_movies.setOnClickListener{
                onClickMovie(atores)
            }

        }

        fun ImageView.loadUrl(url: String) {

            val imageLoader = ImageLoader.Builder(context)
                .componentRegistry {
                    add(SvgDecoder(context))
                }
                .build()

            val request = ImageRequest.Builder(context).data(url)
                .target(this)
                .build()

            imageLoader.enqueue(request)

        }

    }
}