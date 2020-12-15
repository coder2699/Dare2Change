package com.example.inout2020_aimers.Milestones

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.mobiwise.materialintro.shape.Focus
import co.mobiwise.materialintro.shape.FocusGravity
import co.mobiwise.materialintro.shape.ShapeType
import co.mobiwise.materialintro.view.MaterialIntroView
import com.example.inout2020_aimers.Milestones.Adapter.GoalsAdapter
import com.example.inout2020_aimers.Milestones.Database.Goals
import com.example.inout2020_aimers.Milestones.Database.GoalsViewModel
import com.example.inout2020_aimers.R
import com.example.inout2020_aimers.databinding.FragmentStartBinding
import com.example.inout2020_aimers.utils.SwipeToDeleteCallback
import com.google.android.material.snackbar.Snackbar

class StartFragment : Fragment() {
    private lateinit var goalsViewModel: GoalsViewModel
    lateinit var goalsAdapter: GoalsAdapter
    private var _binding: FragmentStartBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        goalsViewModel = ViewModelProviders.of(this).get(GoalsViewModel::class.java)
        goalsAdapter = GoalsAdapter(goalsViewModel, binding.rv.rootView, activity)
        introFab()
        binding.rv.adapter = goalsAdapter
        binding.rv.layoutManager = LinearLayoutManager(context)
        enableSwipeToDeleteAndUndo(goalsAdapter)
        goalsViewModel.allLists.observe(viewLifecycleOwner, Observer { list ->
            if (list.isEmpty()) {
                binding.goalAnim.visibility = View.VISIBLE
            } else {
                binding.goalAnim.visibility = View.GONE
            }
            goalsAdapter.submitList(list)
            goalsAdapter.goalsList = list as ArrayList<Goals>
        })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_addGoalFragment)
        }
        return binding.root
    }

    private fun enableSwipeToDeleteAndUndo(goalsAdapter: GoalsAdapter) {
        val swipeToDeleteCallback = object : SwipeToDeleteCallback(context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                val position = viewHolder.adapterPosition
                goalsAdapter.removeitem(position)
                val item = goalsAdapter.getList()[position]
                AlertDialog.Builder(requireContext())
                    .setTitle("Delete Profile")
                    .setMessage("Are you sure you want to delete this goal?")
                    .setPositiveButton("Yes") { _, dialogInterface ->
                        Snackbar
                            .make(
                                binding.coordLayout,
                                "Item is removed from the list.",
                                Snackbar.LENGTH_SHORT
                            )
                            .show()
                    }
                    .setNegativeButton("No") { _, dialogInterface ->
                        goalsAdapter.restoreItem(item, position)
                    }
                    .setCancelable(false)
                    .show()
            }
        }

        val itemTouchhelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchhelper.attachToRecyclerView(binding.rv)
    }
    private fun introFab() {
        MaterialIntroView.Builder(activity)
            .enableDotAnimation(false)
            .enableIcon(true)
            .setFocusGravity(FocusGravity.CENTER)
            .setFocusType(Focus.ALL)
            .setDelayMillis(500)
            .enableFadeAnimation(true)
            .performClick(false)
            .dismissOnTouch(true)
            .setInfoText("Click the + sign to set a new goal")
            .setShape(ShapeType.CIRCLE)
            .setTarget(binding.floatingActionButton)
            .setUsageId("intro_card_1") // THIS SHOULD BE UNIQUE ID
            .show()
    }
}