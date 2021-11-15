package br.com.cotemig.jose.matheus.moviesandactors.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.Movie
import com.bumptech.glide.Glide

class MoviesAdapter(var context: Context, var list: List<Movie>) : BaseAdapter() {

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view = LayoutInflater.from(context).inflate(R.layout.item_movies,p2,false)

        var name_movie = view.findViewById<TextView>(R.id.name_movie)
        name_movie.text = list[p0].title

        var foto = view.findViewById<ImageView>(R.id.image_movie)
        Glide.with(view).load("https://image.tmdb.org/t/p/w500".plus(list[p0].backdrop_path)).into(foto)

        return view
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return ""
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

}