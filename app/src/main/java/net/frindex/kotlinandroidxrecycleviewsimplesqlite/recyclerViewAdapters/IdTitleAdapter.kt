package net.frindex.kotlinandroidxrecycleviewsimplesqlite.recyclerViewAdapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import net.frindex.kotlinandroidxrecycleviewsimplesqlite.R
import kotlinx.android.synthetic.main.adapter_id_title_item_layout.view.*

class IdTitleAdapter(val list: ArrayList<IdTitleDataClass>,val clickLambda: (Int)->Unit) : RecyclerView.Adapter<IdTitleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IdTitleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_id_title_item_layout,parent,false)
        return IdTitleViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: IdTitleViewHolder, position: Int) {
        val currentItem = list[position]
        holder.textViewTitle.text = currentItem.title
        holder.itemView.setOnClickListener {
            clickLambda(currentItem.id)
        }
    }

} // IdTitleAdapter

class IdTitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val textViewTitle: TextView = itemView.textViewTitle

} // IdTitleViewHolder