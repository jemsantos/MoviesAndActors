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
import br.com.cotemig.jose.matheus.moviesandactors.models.ProviderStreaming
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.bumptech.glide.Glide

class ProviderStreamingAdapter(var context: Context, var list: List<ProviderStreaming>, var onClickProviderStreaming: (ProviderStreaming) -> Unit) :
    RecyclerView.Adapter<ProviderStreamingAdapter.ProviderStreamingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProviderStreamingHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_provider, parent, false)
        return ProviderStreamingHolder(view)
    }

    override fun onBindViewHolder(holder: ProviderStreamingHolder, position: Int) {
        holder.bind(list[position], onClickProviderStreaming)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ProviderStreamingHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bind(provider: ProviderStreaming, onClickProviderStreaming: (ProviderStreaming) -> Unit) {
            var name = view.findViewById<TextView>(R.id.name)
            name.text = provider.provider_name

            var logo = view.findViewById<ImageView>(R.id.logo)
            provider.logo_path?.let { url ->
                // logo.loadUrl(url)
                Glide.with(view).load("https://image.tmdb.org/t/p/w500".plus(url)).into(logo)
            }

            var card = view.findViewById<CardView>(R.id.card)
            card.setOnClickListener{
                onClickProviderStreaming(provider)
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