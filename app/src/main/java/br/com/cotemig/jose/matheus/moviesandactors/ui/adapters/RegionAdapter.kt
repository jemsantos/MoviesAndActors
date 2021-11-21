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
import br.com.cotemig.jose.matheus.moviesandactors.models.AvailableRegions
import br.com.cotemig.jose.matheus.moviesandactors.models.Region
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.bumptech.glide.Glide

class RegionAdapter(var context: Context, var list: List<Region>, var onClickRegion: (Region) -> Unit) :
    RecyclerView.Adapter<RegionAdapter.RegionHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_region, parent, false)
        return RegionHolder(view)
    }

    override fun onBindViewHolder(holder: RegionHolder, position: Int) {
        holder.bind(list[position], onClickRegion)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class RegionHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bind(region: Region, onClickRegion: (Region) -> Unit) {
            var iso = view.findViewById<TextView>(R.id.iso)
            iso.text = region.iso_3166_1

            var englishName = view.findViewById<TextView>(R.id.englishName)
            englishName.text = region.english_name

            var nativeName = view.findViewById<TextView>(R.id.nativeName)
            nativeName.text = region.native_name

            var card = view.findViewById<CardView>(R.id.card)
            card.setOnClickListener{
                onClickRegion(region)
            }
        }

    }

}