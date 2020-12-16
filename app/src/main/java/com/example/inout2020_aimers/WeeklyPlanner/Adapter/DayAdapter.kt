package com.example.inout2020_aimers.WeeklyPlanner.Adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.inout2020_aimers.R
import com.example.inout2020_aimers.WeeklyPlanner.Database.Day
import com.example.inout2020_aimers.WeeklyPlanner.Database.DayViewModel
import com.example.inout2020_aimers.databinding.ItemWeekdayBinding
import com.example.inout2020_aimers.utils.Snacker
import kotlinx.android.synthetic.main.dialog.view.*

class DayAdapter(
    val dayViewModel: DayViewModel,
    val parentView: View
) :
    ListAdapter<Day, DayAdapter.ViewHolder>(
        ListDiffCallbacks()
    ) {

    var dayList = ArrayList<Day>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemWeekdayBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, dayViewModel, parentView.context)
    }

    class ViewHolder(val binding: ItemWeekdayBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Day, dayViewModel: DayViewModel, context: Context) {
            binding.dayName.text = item.dayName
            if (item.dayTask != "")
                binding.dayTask.text = item.dayTask
            binding.dayTask.setOnClickListener {
                val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog, null)
                val mBuilder = androidx.appcompat.app.AlertDialog.Builder(context)
                    .setView(mDialogView)
                    .setTitle("Add tasks for ${item.dayName}")
                val mAlertDialog = mBuilder.show()
                mDialogView.dialogNameEt.setText(item.dayTask)

                mDialogView.dialogCancelBtn.setOnClickListener {
                    mAlertDialog.dismiss()
                }
                mDialogView.dialogSuccessBtn.setOnClickListener {
                    val task = mDialogView.dialogNameEt.text.toString()
                    item.dayTask = task
                    dayViewModel.update(item)
                    if (item.dayTask != "") {
                        binding.dayTask.text = item.dayTask
                        mAlertDialog.dismiss()
                    } else
                        Snacker(itemView, "This field can't be empty").error()
                }
            }
            binding.itemBin.setOnClickListener {
                item.dayTask=""
                dayViewModel.update(item)
                binding.dayTask.text=item.dayTask
            }
        }
    }

    class ListDiffCallbacks : DiffUtil.ItemCallback<Day>() {
        override fun areItemsTheSame(oldItem: Day, newItem: Day): Boolean {
            return oldItem.dayId == newItem.dayId
        }

        override fun areContentsTheSame(oldItem: Day, newItem: Day): Boolean {
            return oldItem == newItem
        }
    }

}
