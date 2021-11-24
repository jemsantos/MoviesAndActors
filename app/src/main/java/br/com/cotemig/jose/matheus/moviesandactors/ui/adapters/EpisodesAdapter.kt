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
import br.com.cotemig.jose.matheus.moviesandactors.models.Episodes
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest

class EpisodesAdapter(var context: Context, var list: List<Episodes>, var onClickEpisode: (Episodes) -> Unit) :
    RecyclerView.Adapter<EpisodesAdapter.EpisodesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesHolder {
        // carregando o xml na memória
        var view = LayoutInflater.from(context).inflate(R.layout.item_episode, parent, false)
        // retornando objeto holder instanciado, passando view no construtor
        return EpisodesHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodesHolder, position: Int) {
        holder.bind(list[position], onClickEpisode)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class EpisodesHolder(var view: View) : RecyclerView.ViewHolder(view) {

        fun  bind(episodes: Episodes, onClickEpisode: (Episodes) -> Unit){

            var name_episode = view.findViewById<TextView>(R.id.name_episode)
            name_episode.text = episodes.name


            var episode_number = view.findViewById<TextView>(R.id.episode_number)
            episode_number.text = episodes.episode_number.toString()

            var air_date = view.findViewById<TextView>(R.id.air_date)
            air_date.text = episodes.air_date


            var overview = view.findViewById<TextView>(R.id.overview)
            overview.text = episodes.overview


            var foto = view.findViewById<ImageView>(R.id.image_episode)

            foto.loadUrl("https://image.tmdb.org/t/p/w500"+episodes.still_path)

            var card_episode = view.findViewById<CardView>(R.id.card_episode)
            card_episode.setOnClickListener{
                onClickEpisode(episodes)
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