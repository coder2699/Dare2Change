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
                imgBook.setImageResource(R.drawable.frog)
            else if(position == 1)
                imgBook.setImageResource(R.drawable.deep_work)
            else if(position == 2)
                imgBook.setImageResource(R.drawable.obstacle)
            else if(position == 3)
                imgBook.setImageResource(R.drawable.habit)
            else if(position == 4)
                imgBook.setImageResource(R.drawable.ikigai)
            else if(position == 5)
                imgBook.setImageResource(R.drawable.no_excuse)
            else if(position == 6)
                imgBook.setImageResource(R.drawable.power)
            else if(position == 7)
                imgBook.setImageResource(R.drawable.war_of_art)
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            if(position == 0)
                intent.setData(Uri.parse("https://www.amazon.in/Eat-That-Frog-Great-Procrastinating/dp/152309513X/ref=sr_1_2?adgrpid=59632320620&dchild=1&ext_vrnc=hi&gclid=CjwKCAiAq8f-BRBtEiwAGr3DgUe_5yn_l4FRmHQjRToCKId-rSBzrDKxz7j4zieQ9dJ-3zQyjuKb1xoCUjIQAvD_BwE&hvadid=294145717093&hvdev=c&hvlocphy=9303891&hvnetw=g&hvqmt=b&hvrand=1567984751751470638&hvtargid=kwd-354485210287&hydadcr=1105_1742571&keywords=eat+that+frog+book&qid=1607671678&sr=8-2&tag=googinhydr1-21"))
            else if(position == 1)
                intent.setData(Uri.parse("https://www.amazon.in/Deep-Work-Focused-Success-Distracted/dp/0349413681/ref=pd_bxgy_img_2/262-8855794-8674807?_encoding=UTF8&pd_rd_i=0349413681&pd_rd_r=b85308d3-8c26-415e-97fc-441b51e278ca&pd_rd_w=n51N4&pd_rd_wg=mzOYr&pf_rd_p=3e9f62d7-4196-4823-b41a-9bba671558ea&pf_rd_r=CZ83Z1PG380VJ0BAZB8K&psc=1&refRID=CZ83Z1PG380VJ0BAZB8K"))
            else if(position == 2)
                intent.setData(Uri.parse("https://www.amazon.in/Obstacle-Way-Timeless-Turning-Triumph/dp/B076YH9WKW/ref=sr_1_1?dchild=1&keywords=The+Obstacle+Is+the+Way+The+Timeless+Art+of+Turning+Trials+into+Triumph&qid=1607671849&sr=8-1"))
            else if(position == 3)
                intent.setData(Uri.parse("https://www.amazon.com/Habits-Highly-Effective-People-Powerful/dp/1451639619"))
            else if(position == 4)
                intent.setData(Uri.parse("https://www.amazon.com/Ikigai-Japanese-Secret-Long-Happy/dp/0143130722/ref=sr_1_1?crid=3JM46ZTZ9YDE1&dchild=1&keywords=ikigai&qid=1607062250&s=books&sprefix=iki%2Cstripbooks-intl-ship%2C458&sr=1-1"))
            else if(position == 5)
                intent.setData(Uri.parse("https://www.amazon.com/dp/B003MSCSHA?plink=7BrdXQRMieA8KJ2M&ref=adblp13nvvxx_0_5_ti"))
            else if(position == 6)
                intent.setData(Uri.parse("https://www.amazon.com/dp/B007EJSMC8?plink=pBUzaD2ZBsvmxENi&ref=adblp13nvvxx_0_6_im"))
            else if(position == 7)
                intent.setData(Uri.parse("https://www.amazon.in/War-Art-Through-Creative-Battles/dp/1936891026/ref=sr_1_1?dchild=1&keywords=The+War+of+Art_+Break+Through+the+Blocks+and+Win+Your+Inner+Creative+Battles&qid=1607671891&sr=8-1"))

            it.context.startActivity(intent)
        }
    }
}