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
import br.com.cotemig.jose.matheus.moviesandactors.models.Serie
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.bumptech.glide.Glide

class SeriesAdapter (var context: Context, var list: List<Serie>, var onClickSerie: (Serie) -> Unit) :
    RecyclerView.Adapter<SeriesAdapter.SeriesHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesHolder {
        // carregando o xml na memÃ³ria
        var view = LayoutInflater.from(context).inflate(R.layout.item_series, parent, false)
        // retornando objeto holder instanciado, passando view no construtor
        return SeriesHolder(view)
    }

    override fun onBindViewHolder(holder: SeriesHolder, position: Int) {
        holder.bind(list[position], onClickSerie)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class SeriesHolder(var view: View) : RecyclerView.ViewHolder(view) {

        fun  bind(serie: Serie, onClickSerie: (Serie) -> Unit){

            var name_serie = view.findViewById<TextView>(R.id.name_serie)
            name_serie.text = serie.name



            var nota_serie = view.findViewById<TextView>(R.id.nota_serie)
            nota_serie.text = serie.vote_average

            var first_air_date = view.findViewById<TextView>(R.id.first_air_date)
            first_air_date.text = serie.first_air_date

            var overview = view.findViewById<TextView>(R.id.overview)
            overview.text = serie.overview

            var foto = view.findViewById<ImageView>(R.id.image_serie)

            foto.loadUrl("https://image.tmdb.org/t/p/w500"+serie.poster_path)

            var card_series = view.findViewById<CardView>(R.id.card_series)
            card_series.setOnClickListener{
                onClickSerie(serie)
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