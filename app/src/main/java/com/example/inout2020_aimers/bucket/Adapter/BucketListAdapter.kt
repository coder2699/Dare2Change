package com.example.inout2020_aimers.bucket.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.inout2020_aimers.bucket.Database.Bucket
import com.example.inout2020_aimers.bucket.Database.ListViewModel
import com.example.inout2020_aimers.databinding.ItemBucketBinding

class BucketListAdapter(
    val listViewModel: ListViewModel,
    val parentView: View
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

}