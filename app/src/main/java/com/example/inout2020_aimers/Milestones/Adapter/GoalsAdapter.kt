package com.example.inout2020_aimers.Milestones.Adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.inout2020_aimers.Milestones.Database.Goals
import com.example.inout2020_aimers.Milestones.Database.GoalsViewModel
import com.example.inout2020_aimers.Milestones.DetailsFragment
import com.example.inout2020_aimers.databinding.ItemGoalsBinding
import co.mobiwise.materialintro.shape.Focus
import co.mobiwise.materialintro.shape.FocusGravity
import co.mobiwise.materialintro.shape.ShapeType
import co.mobiwise.materialintro.view.MaterialIntroView
class GoalsAdapter(
    val goalsViewModel: GoalsViewModel,
    val parentView: View,
    val activity: FragmentActivity?
) :
    ListAdapter<Goals, GoalsAdapter.ViewHolder>(
        ListDiffCallbacks()
    ) {

    var goalsList = ArrayList<Goals>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGoalsBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, goalsViewModel, parentView)
        if (position == 0) {
            introDetail(holder.binding.goalCard)
        }
    }

    class ViewHolder(val binding: ItemGoalsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Goals, goalsViewModel: GoalsViewModel, parentView: View) {
            binding.mainGoal.text = item.mainGoal
            binding.timeStamp.text = "Complete By :- ${item.mainGoalDate}"
            binding.goalCard.setOnClickListener {
                val args = Bundle()
                args.putParcelable("Goals", item)
                val dialog = DetailsFragment.newInstance(args)
                dialog.show(
                    (parentView.context as FragmentActivity).supportFragmentManager,
                    "DialogFragment"
                )
            }
        }
    }

    class ListDiffCallbacks : DiffUtil.ItemCallback<Goals>() {
        override fun areItemsTheSame(oldItem: Goals, newItem: Goals): Boolean {
            return oldItem.goalId == newItem.goalId
        }

        override fun areContentsTheSame(oldItem: Goals, newItem: Goals): Boolean {
            return oldItem == newItem
        }
    }
    fun getList() = goalsList

    fun removeitem(position: Int) {
        goalsViewModel.delete(goalsList[position])
        notifyItemRemoved(position)
    }

    fun restoreItem(goals:Goals, position: Int) {
        goalsList.add(position, goals)
        notifyItemChanged(position)
        goalsViewModel.insert(goals)
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
            .setInfoText("Slide the goal towards left to delete it")
            .setShape(ShapeType.CIRCLE)
            .setTarget(view)
            .setUsageId("intro_card") // THIS SHOULD BE UNIQUE ID
            .show()
    }

    private fun introDetail(view: View) {
        MaterialIntroView.Builder(activity)
            .enableDotAnimation(true)
            .enableIcon(true)
            .setFocusGravity(FocusGravity.CENTER)
            .setFocusType(Focus.NORMAL)
            .setDelayMillis(400)
            .enableFadeAnimation(true)
            .performClick(false)
            .dismissOnTouch(true)
            .setInfoText("Click on the goal to get sub-goal details")
            .setShape(ShapeType.CIRCLE)
            .setTarget(view)
            .setUsageId("intro_card_2") // THIS SHOULD BE UNIQUE ID
            .setListener {
                introDelete(view)
            }
            .show()
    }
}