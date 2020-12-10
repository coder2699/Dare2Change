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
): RecyclerView.Adapter<ResourceAdapter.contentAdaterViewHolder>() {
    inner class contentAdaterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): contentAdaterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_resources, parent, false)
        return contentAdaterViewHolder(view)
    }
    override fun getItemCount(): Int =  ResourceItem.size

    override fun onBindViewHolder(holder: contentAdaterViewHolder, position: Int) {
        holder.itemView.apply {
            resourceText.text = ResourceItem[position].text1
            if(position == 0)
                imgBook.setImageResource(R.drawable.habit)
            else if(position == 1)
                imgBook.setImageResource(R.drawable.ikigai)
            else if(position == 2)
                imgBook.setImageResource(R.drawable.no_excuse)
            else if(position == 3)
                imgBook.setImageResource(R.drawable.power)
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            if(position == 0)
                intent.setData(Uri.parse("https://www.amazon.com/Habits-Highly-Effective-People-Powerful/dp/1451639619"))
            else if(position == 1)
                intent.setData(Uri.parse("https://www.amazon.com/Ikigai-Japanese-Secret-Long-Happy/dp/0143130722/ref=sr_1_1?crid=3JM46ZTZ9YDE1&dchild=1&keywords=ikigai&qid=1607062250&s=books&sprefix=iki%2Cstripbooks-intl-ship%2C458&sr=1-1"))
            else if(position == 2)
                intent.setData(Uri.parse("https://www.amazon.com/dp/B003MSCSHA?plink=7BrdXQRMieA8KJ2M&ref=adblp13nvvxx_0_5_ti"))
            else if(position == 3)
                intent.setData(Uri.parse("https://www.amazon.com/dp/B007EJSMC8?plink=pBUzaD2ZBsvmxENi&ref=adblp13nvvxx_0_6_im"))

            it.context.startActivity(intent)
        }
    }
}