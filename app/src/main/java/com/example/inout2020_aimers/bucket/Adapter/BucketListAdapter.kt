package com.example.inout2020_aimers.bucket.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.inout2020_aimers.bucket.Database.Bucket
import com.example.inout2020_aimers.bucket.Database.ListViewModel
import com.example.inout2020_aimers.databinding.ItemBucketBinding
import co.mobiwise.materialintro.shape.Focus
import co.mobiwise.materialintro.shape.FocusGravity
import co.mobiwise.materialintro.shape.ShapeType
import co.mobiwise.materialintro.view.MaterialIntroView

class BucketListAdapter(
    val listViewModel: ListViewModel,
    val parentView: View,
    val activity: FragmentActivity?
) :
    ListAdapter<Bucket, BucketListAdapter.ViewHolder>(
        ListDiffCallbacks()
    ) {

    var bucketList = ArrayList<Bucket>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBucketBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, listViewModel)
        if (position == 0) {
            introDelete(holder.binding.itemText)
        }
    }

    class ViewHolder(val binding: ItemBucketBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Bucket, listViewModel: ListViewModel) {
            binding.itemText.text = item.task
            binding.itemCheck.isChecked = item.check
            binding.itemCheck.setOnClickListener {
                item.check = binding.itemCheck.isChecked
                listViewModel.update(item)
            }
        }
    }

    class ListDiffCallbacks : DiffUtil.ItemCallback<Bucket>() {
        override fun areItemsTheSame(oldItem: Bucket, newItem: Bucket): Boolean {
            return oldItem.listId == newItem.listId
        }

        override fun areContentsTheSame(oldItem: Bucket, newItem: Bucket): Boolean {
            return oldItem == newItem
        }
    }
    fun getList() = bucketList

    fun removeitem(position: Int) {
        listViewModel.delete(bucketList[position])
        notifyItemRemoved(position)
    }

    fun restoreItem(bucket: Bucket, position: Int) {
        bucketList.add(position, bucket)
        notifyItemChanged(position)
        listViewModel.insert(bucket)
    }
    private fun introDelete(view: View) {
        MaterialIntroView.Builder(activity)
            .enableDotAnimation(false)
            .enableIcon(true)
            .setFocusGravity(FocusGravity.RIGHT)
            .setFocusType(Focus.MINIMUM)
            .setDelayMillis(400)
            .enableFadeAnimation(true)
            .performClick(false)
            .dismissOnTouch(true)
            .setInfoText("Slide the item towards left to delete it")
            .setShape(ShapeType.CIRCLE)
            .setTarget(view)
            .setUsageId("intro_card_5") // THIS SHOULD BE UNIQUE ID
            .show()
    }
}