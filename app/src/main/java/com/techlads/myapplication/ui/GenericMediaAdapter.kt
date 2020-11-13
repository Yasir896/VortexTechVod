package com.techlads.myapplication.ui

import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.techlads.myapplication.data.GenericMedia
import com.techlads.myapplication.utils.addOverlayedBackground
import kotlinx.android.synthetic.main.generic_card_layout.view.*


/**
 * Created by Yasir on 11/3/2020.
 */

class GenericMediaAdapter(@LayoutRes val layout : Int, var listener: OnRecyclerItemClicked) : RecyclerView.Adapter<GenericMediaAdapter.GenricViewHolder>() {

    private var media = arrayListOf<GenericMedia>()


    class GenricViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setViewHolder(media: GenericMedia?, listener: OnRecyclerItemClicked) {
            itemView?.cardTitleTv?.text = media?.title
            android.os.Handler(Looper.getMainLooper()).postDelayed({
                media?.imageUrl?.let {
                    itemView?.cardContainerRl?.addOverlayedBackground(it)
                }
            }, 400)
            itemView.setOnClickListener {
                listener.onItemClicked(media, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenricViewHolder {
        return GenricViewHolder(LayoutInflater.from(parent.context).inflate(layout , parent, false))
    }

    override fun getItemCount(): Int {
        return media.size
    }

    override fun onBindViewHolder(holder: GenricViewHolder, position: Int) {
        holder.setViewHolder(media.getOrNull(position), listener)
    }

    fun update(media: ArrayList<GenericMedia>) {
        this.media = media
        notifyDataSetChanged()
    }

    interface OnRecyclerItemClicked {
        fun onItemClicked(media: GenericMedia?, position: Int)
    }


}