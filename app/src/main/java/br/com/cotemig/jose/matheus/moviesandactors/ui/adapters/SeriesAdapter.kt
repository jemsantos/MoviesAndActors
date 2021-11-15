package br.com.cotemig.jose.matheus.moviesandactors.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.Serie
import com.bumptech.glide.Glide

class SeriesAdapter (var context: Context, var list: List<Serie>) : BaseAdapter(){

    //metodos abastratos da base adapter
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = LayoutInflater.from(context).inflate(R.layout.item_series, parent,false)


        var name_serie = view.findViewById<TextView>(R.id.name_serie)
        name_serie.text = list[position].name

        var foto = view.findViewById<ImageView>(R.id.image_serie)
        Glide.with(view).load("https://image.tmdb.org/t/p/w500".plus(list[position].backdrop_path)).into(foto)



        return view
    }

    override fun getItem(position: Int): Any {
        return ""
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }

}