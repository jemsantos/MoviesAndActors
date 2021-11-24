package br.com.cotemig.jose.matheus.moviesandactors.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.Movie
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.bumptech.glide.Glide

class MoviesAdapter(var context: Context, var list: List<Movie>, var onClickMovie: (Movie) -> Unit) :
        RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        // carregando o xml na memÃ³ria
        var view = LayoutInflater.from(context).inflate(R.layout.item_movies, parent, false)
        // retornando objeto holder instanciado, passando view no construtor
        return MoviesHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        holder.bind(list[position], onClickMovie)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MoviesHolder(var view: View) : RecyclerView.ViewHolder(view) {

        fun  bind(movie: Movie, onClickMovie: (Movie) -> Unit){
            var name_movie = view.findViewById<TextView>(R.id.name_movie)
            name_movie.text = movie.title

            var nota_movie = view.findViewById<TextView>(R.id.nota_movie)
            nota_movie.text = movie.vote_average

            var release_date = view.findViewById<TextView>(R.id.data_movie)
            release_date.text = movie.release_date

            var overview = view.findViewById<TextView>(R.id.overview)
            overview.text = movie.overview


            var foto = view.findViewById<ImageView>(R.id.image_movie)

            foto.loadUrl("https://image.tmdb.org/t/p/w500"+movie.poster_path)

            var card_movies = view.findViewById<CardView>(R.id.card_movies)
            card_movies.setOnClickListener{
                onClickMovie(movie)
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