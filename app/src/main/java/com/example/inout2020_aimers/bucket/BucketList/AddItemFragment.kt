package com.example.inout2020_aimers.bucket.BucketList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.inout2020_aimers.R
import com.example.inout2020_aimers.bucket.Database.Bucket
import com.example.inout2020_aimers.bucket.Database.ListViewModel
import com.example.inout2020_aimers.databinding.FragmentAddItemBinding
import com.example.inout2020_aimers.utils.Snacker
import com.google.android.material.snackbar.Snackbar

class AddItemFragment : Fragment() {
    private lateinit var listViewModel: ListViewModel
    private var _binding: FragmentAddItemBinding? = null
    private val binding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddItemBinding.inflate(inflater, container, false)
        binding.makeList.setOnClickListener {
            listViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
            if(binding.userToDoEditText.text.toString() == ""){
                Snacker(it,"This Field can't be empty").error()
            }
            else {
                val bucket = Bucket(
                    task = binding.userToDoEditText.text.toString(),
                    check = false
                )
                bucket.listId = System.currentTimeMillis()
                listViewModel.insert(bucket)
                findNavController().navigate(R.id.action_addItemFragment_to_displayFragment)
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}