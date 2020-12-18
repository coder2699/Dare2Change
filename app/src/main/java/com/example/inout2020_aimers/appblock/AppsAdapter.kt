package com.example.inout2020_aimers.appblock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inout2020_aimers.R
import kotlinx.android.synthetic.main.item_select_apps.view.*

class AppsAdapter(val appList : ArrayList<AppListModel>) : RecyclerView.Adapter<AppsAdapter.AppsViewHolder>() {

    inner class AppsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppsViewHolder {
        return AppsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_select_apps,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AppsViewHolder, position: Int) {

        val currentItem = appList[position]

        holder.itemView.apply {

            itemAppIcon.setImageDrawable(currentItem.icon)
            itemAppname.text = currentItem.name

        }
    }

    override fun getItemCount(): Int {
        return appList.size
    }

}