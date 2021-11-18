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
import br.com.cotemig.jose.matheus.moviesandactors.models.Cast
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest

class CastAdapter(var context: Context,var list: List<Cast>):
    RecyclerView.Adapter<CastAdapter.CastHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_cast, parent, false)
        return CastHolder(view)
    }

    override fun onBindViewHolder(holder: CastHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class CastHolder(var view: View) : RecyclerView.ViewHolder(view) {

        fun  bind(cast: Cast){

            var name_ator = view.findViewById<TextView>(R.id.name_ator)
            name_ator.text = cast.name

            var name_personagem = view.findViewById<TextView>(R.id.name_personagem)
            name_personagem.text = cast.character

            var foto = view.findViewById<ImageView>(R.id.image_cast)

            foto.loadUrl("https://image.tmdb.org/t/p/w500"+cast.profile_path)


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