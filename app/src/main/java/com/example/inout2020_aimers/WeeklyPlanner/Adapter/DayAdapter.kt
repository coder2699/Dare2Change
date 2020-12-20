package com.example.inout2020_aimers.WeeklyPlanner.Adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
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

        @SuppressLint("ResourceAsColor")
        fun bind(item: Day, dayViewModel: DayViewModel, context: Context) {
            binding.dayName.text = item.dayName
            binding.dayTask.text = item.dayTask
            if(item.done==0)
                binding.box.setBackgroundResource(R.drawable.rect)
            else if(item.done==1)
                binding.box.setBackgroundResource(R.color.colorSuccess)
            else
                binding.box.setBackgroundResource(R.color.colorError)

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
                    item.dayTask = mDialogView.dialogNameEt.text.toString()
                    dayViewModel.update(item)
                    if (item.dayTask != "") {
                        binding.dayTask.text = item.dayTask
                        mAlertDialog.dismiss()
                    } else
                        Snacker(itemView, "This field can't be empty").error()
                }
            }
            binding.itemCheck.setOnClickListener {
                binding.box.setBackgroundResource(R.color.colorSuccess)
                item.done=1
                dayViewModel.update(item)
            }
            binding.itemCancel.setOnClickListener {
                binding.box.setBackgroundResource(R.color.colorError)
                item.done=-1
                dayViewModel.update(item)
            }
            binding.itemBin.setOnClickListener {
                item.dayTask = ""
                dayViewModel.update(item)
                binding.dayTask.text = item.dayTask
                binding.box.setBackgroundResource(R.drawable.rect)
                item.done=0
                dayViewModel.update(item)
            }
        }
    }

    class ListDiffCallbacks : DiffUtil.ItemCallback<Day>() {
        override fun areItemsTheSame(oldItem: Day, newItem: Day): Boolean {
            return oldItem.dayName == newItem.dayName
        }

        override fun areContentsTheSame(oldItem: Day, newItem: Day): Boolean {
            return oldItem == newItem
        }
    }

}
