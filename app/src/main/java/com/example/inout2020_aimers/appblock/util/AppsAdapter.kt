package com.example.inout2020_aimers.appblock.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inout2020_aimers.R
import com.example.inout2020_aimers.appblock.AppListModel
import com.example.inout2020_aimers.appblock.database.BlockedApps
import kotlinx.android.synthetic.main.item_select_apps.view.*

class AppsAdapter(
    private val appList : ArrayList<AppListModel>,
    private val viewModel: BlockerViewModel,
    private val blockedApps : ArrayList<BlockedApps>
    ) : RecyclerView.Adapter<AppsAdapter.AppsViewHolder>() {

    inner class AppsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    private val TAG = "AppsAdapter"
    
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

        holder.setIsRecyclable(false)

        holder.itemView.apply {

            itemAppIcon.setImageDrawable(currentItem.icon)
            itemAppname.text = currentItem.name

            for (app in blockedApps){
                if (app.packageName == currentItem.packageName){
                    itemAppCheckbox.isChecked = true
                    break
                }
            }


            itemAppCheckbox.setOnCheckedChangeListener { compoundButton, b ->

                if (b){
                    viewModel.upsert(BlockedApps(currentItem.packageName))
                }else{
                    viewModel.delete(BlockedApps(currentItem.packageName))
                }

            }


        }
    }

    override fun getItemCount(): Int {
        return appList.size
    }

}