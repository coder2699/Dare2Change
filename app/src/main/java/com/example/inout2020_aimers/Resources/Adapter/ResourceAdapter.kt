package com.example.inout2020_aimers.Resources.Adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inout2020_aimers.R
import kotlinx.android.synthetic.main.view_resources.view.*

class ResourceAdapter(
    var ResourceItem: MutableList<com.example.inout2020_aimers.Resources.Resources>

) : RecyclerView.Adapter<ResourceAdapter.contentAdaterViewHolder>() {
    inner class contentAdaterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): contentAdaterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_resources, parent, false)
        return contentAdaterViewHolder(view)
    }

    override fun getItemCount(): Int = ResourceItem.size

    override fun onBindViewHolder(holder: contentAdaterViewHolder, position: Int) {
        holder.itemView.apply {
            resourceText.text = ResourceItem[position].text1
            if (position == 0)
                imgBook.setImageResource(R.drawable.article_a)
            else if (position == 1)
                imgBook.setImageResource(R.drawable.twenty)
            else if (position == 2)
                imgBook.setImageResource(R.drawable.article_c)
            else if (position == 3)
                imgBook.setImageResource(R.drawable.article_d)
            else if (position == 4)
                imgBook.setImageResource(R.drawable.shiv_khera)
            else if (position == 5)
                imgBook.setImageResource(R.drawable.robin_sharma)
            else if (position == 6)
                imgBook.setImageResource(R.drawable.gary_vee)
            else if (position == 7)
                imgBook.setImageResource(R.drawable.grant)
            else if (position == 8)
                imgBook.setImageResource(R.drawable.brian_tracy)
            else if (position == 9)
                imgBook.setImageResource(R.drawable.jim_rohn)
            else if (position == 10)
                imgBook.setImageResource(R.drawable.frog)
            else if (position == 11)
                imgBook.setImageResource(R.drawable.deep_work)
            else if (position == 12)
                imgBook.setImageResource(R.drawable.obstacle)
            else if (position == 13)
                imgBook.setImageResource(R.drawable.habit)
            else if (position == 14)
                imgBook.setImageResource(R.drawable.ikigai)
            else if (position == 15)
                imgBook.setImageResource(R.drawable.no_excuse)
            else if (position == 16)
                imgBook.setImageResource(R.drawable.power)
            else if (position == 17)
                imgBook.setImageResource(R.drawable.war_of_art)
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(ResourceItem[position].url)
            }
        }
    }

    private var onItemClickListener:((String)->Unit)? = null

    fun setOnItemClickListner(listener:(String) -> Unit){
        onItemClickListener = listener
    }

}