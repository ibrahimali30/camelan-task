package com.ibrahim.camelan_task.foursquare.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.camelan_task.R
import kotlin.collections.ArrayList

class PlacesAdapter(val data: ArrayList<Any> = java.util.ArrayList()) :
    RecyclerView.Adapter<PlacesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setList(list: List<Any>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(model: Any) {


        }


    }
}