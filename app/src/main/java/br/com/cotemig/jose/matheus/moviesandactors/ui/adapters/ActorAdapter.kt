package br.com.cotemig.jose.matheus.moviesandactors.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.Atores

class ActorAdapter(var context: Context, var list: List<Atores>, var onClickActor: (Atores) -> Unit) :
    RecyclerView.Adapter<ActorAdapter.ActorHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_person, parent, false)
        return ActorHolder(view)
    }

    override fun onBindViewHolder(holder: ActorHolder, position: Int) {
        holder.bind(list[position], onClickActor)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ActorHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bind(actor: Atores, onClickActor: (Atores) -> Unit) {
            var name = view.findViewById<TextView>(R.id.name)
            name.text = actor.name

            var knownForDepartment = view.findViewById<TextView>(R.id.knownForDepartment)
            knownForDepartment.text = actor.known_for_department

            var card = view.findViewById<CardView>(R.id.card)
            card.setOnClickListener{
                onClickActor(actor)
            }
        }

    }

}