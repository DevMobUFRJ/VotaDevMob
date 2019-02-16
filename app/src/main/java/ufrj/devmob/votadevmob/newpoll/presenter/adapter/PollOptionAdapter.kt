package ufrj.devmob.votadevmob.newpoll.presenter.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.card_poll_option.view.*
import ufrj.devmob.votadevmob.R

class PollOptionAdapter (private val options: Map<String, Int>, private val clickListener: (View) -> Unit, private val context: Context): RecyclerView.Adapter<ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_poll_option, parent, false))
    }

    override fun getItemCount(): Int {
        return options.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(options.keys.elementAt(position), clickListener)
    }
}

class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(optionText: String, clickListener: (View) -> Unit) {
        itemView.text_card_poll.text = optionText
        itemView.setOnClickListener { clickListener(itemView) }
    }
}