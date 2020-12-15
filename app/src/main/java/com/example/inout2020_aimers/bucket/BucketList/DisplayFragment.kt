package com.example.inout2020_aimers.bucket.BucketList

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
import com.example.inout2020_aimers.R
import com.example.inout2020_aimers.bucket.Adapter.BucketListAdapter
import com.example.inout2020_aimers.bucket.Database.Bucket
import com.example.inout2020_aimers.bucket.Database.ListViewModel
import com.example.inout2020_aimers.databinding.FragmentDisplayBinding
import com.example.inout2020_aimers.utils.SwipeToDeleteCallback
import com.google.android.material.snackbar.Snackbar

class DisplayFragment : Fragment() {
    private lateinit var listViewModel: ListViewModel
    lateinit var bucketListAdapter: BucketListAdapter
    private var _binding: FragmentDisplayBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDisplayBinding.inflate(inflater, container, false)
        listViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        bucketListAdapter = BucketListAdapter(listViewModel, binding.rv.rootView,activity)
        introFab()
        binding.rv.adapter = bucketListAdapter
        binding.rv.layoutManager = LinearLayoutManager(context)
        enableSwipeToDeleteAndUndo(bucketListAdapter)
        listViewModel.allLists.observe(viewLifecycleOwner, Observer { list ->
            if (list.isEmpty()) {
                binding.anim.visibility = View.VISIBLE
            } else {
                binding.anim.visibility = View.GONE
            }
            bucketListAdapter.submitList(list)
            bucketListAdapter.bucketList = list as ArrayList<Bucket>
        })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_displayFragment_to_addItemFragment)
        }
        return binding.root
    }

    private fun enableSwipeToDeleteAndUndo(bucketListAdapter: BucketListAdapter) {
        val swipeToDeleteCallback = object : SwipeToDeleteCallback(context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                val position = viewHolder.adapterPosition
                bucketListAdapter.removeitem(position)
                val item = bucketListAdapter.getList()[position]
                AlertDialog.Builder(requireContext())
                    .setTitle("Delete Profile")
                    .setMessage("Are you sure you want to delete this profile?")
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
                        bucketListAdapter.restoreItem(item, position)
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
            .setInfoText("Click the + sign to add a new item in your bucket")
            .setShape(ShapeType.CIRCLE)
            .setTarget(binding.floatingActionButton)
            .setUsageId("intro_card_6") // THIS SHOULD BE UNIQUE ID
            .show()
    }

}