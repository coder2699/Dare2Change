package com.example.inout2020_aimers.Motivation.Fragment.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.inout2020_aimers.Motivation.Fragment.GalleryImageClickListener
import com.example.inout2020_aimers.Motivation.Fragment.GlideApp
import com.example.inout2020_aimers.R
import kotlinx.android.synthetic.main.item_motivation_image.view.*

class MotivationImageAdapter(private val itemList: ArrayList<com.example.inout2020_aimers.Motivation.Fragment.Image>) : RecyclerView.Adapter<MotivationImageAdapter.ViewHolder>() {
    private var context: Context? = null
    var listener: GalleryImageClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotivationImageAdapter.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_motivation_image, parent,
            false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return itemList.size
    }
    override fun onBindViewHolder(holder: MotivationImageAdapter.ViewHolder, position: Int) {
        holder.bind()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val image = itemList.get(adapterPosition)
            // load image
            GlideApp.with(context!!)
                .load(image.imageUrl)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemView.ivGalleryImage)
            // adding click or tap handler for our image layout
            itemView.container.setOnClickListener {
                listener?.onClick(adapterPosition)
            }
        }
    }
}