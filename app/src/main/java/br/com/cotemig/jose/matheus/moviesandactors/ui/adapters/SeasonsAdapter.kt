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
import br.com.cotemig.jose.matheus.moviesandactors.models.Season
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest

class SeasonsAdapter(var context: Context, var list: List<Season>, var onClickSeason: (Season) -> Unit) :
    RecyclerView.Adapter<SeasonsAdapter.SeasonsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonsHolder {
        // carregando o xml na memória
        var view = LayoutInflater.from(context).inflate(R.layout.item_season, parent, false)
        // retornando objeto holder instanciado, passando view no construtor
        return SeasonsHolder(view)
    }

    override fun onBindViewHolder(holder: SeasonsHolder, position: Int) {
        holder.bind(list[position], onClickSeason)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class SeasonsHolder(var view: View) : RecyclerView.ViewHolder(view) {

        fun  bind(season: Season, onClickMovie: (Season) -> Unit){

            var name_season = view.findViewById<TextView>(R.id.name_season)
            name_season.text = season.name


            var numero_episodios_season = view.findViewById<TextView>(R.id.numero_episodios_season)
            numero_episodios_season.text = season.season_number.toString()

            var air_date_season = view.findViewById<TextView>(R.id.air_date_season)
            air_date_season.text = season.air_date


            var overview_season = view.findViewById<TextView>(R.id.overview_season)
            overview_season.text = season.overview


            var foto = view.findViewById<ImageView>(R.id.image_season)

            foto.loadUrl("https://image.tmdb.org/t/p/w500"+season.poster_path)

            var card_seasons = view.findViewById<CardView>(R.id.card_season)
            card_seasons.setOnClickListener{
                onClickMovie(season)
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