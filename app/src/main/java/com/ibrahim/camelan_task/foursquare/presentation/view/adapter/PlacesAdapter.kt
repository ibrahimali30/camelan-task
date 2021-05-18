package com.ibrahim.camelan_task.foursquare.presentation.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ibrahim.camelan_task.R
import com.ibrahim.camelan_task.foursquare.data.model.photo.PlacePhotos
import com.ibrahim.camelan_task.foursquare.presentation.model.PlacesUiModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item.view.*
import kotlin.collections.ArrayList

class PlacesAdapter(val data: ArrayList<PlacesUiModel> = java.util.ArrayList()) :
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

    fun setList(list: List<PlacesUiModel>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("CheckResult")
        fun bind(model: PlacesUiModel) {

            if (model.subject.hasValue()){
                loadImage(model.subject.value!!, itemView.ivPlace)
            }else{
                model.subject
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe ({
                        loadImage(it, itemView.ivPlace)
                    }, {

                    })
            }

        }

        private fun loadImage(placePhotoUrl: String, ivPlace: ImageView) {
            Glide.with(itemView)
                .load(placePhotoUrl)
                .into(ivPlace)
        }


    }
}