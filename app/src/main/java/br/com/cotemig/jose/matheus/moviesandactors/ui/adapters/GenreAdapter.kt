package br.com.cotemig.jose.matheus.moviesandactors.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.Genre

class GenreAdapter(var context: Context, var list: List<Genre>, var onClickGenre: (Genre) -> Unit) :
    RecyclerView.Adapter<GenreAdapter.GenreHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_genre, parent, false)
        return GenreHolder(view)
    }

    override fun onBindViewHolder(holder: GenreHolder, position: Int) {
        holder.bind(list[position], onClickGenre)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class GenreHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bind(genre: Genre, onClickGenre: (Genre) -> Unit) {
            var id = view.findViewById<TextView>(R.id.idGenre)
            id.text = genre.id.toString()

            var name = view.findViewById<TextView>(R.id.name)
            name.text = genre.name

            var card = view.findViewById<CardView>(R.id.card)
            card.setOnClickListener{
                onClickGenre(genre)
            }
        }
    }

}