package com.example.inout2020_aimers.Exercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.inout2020_aimers.R
import com.example.inout2020_aimers.databinding.FragmentStartExerciseBinding

class StartExerciseFragment : Fragment() {
    private var _binding: FragmentStartExerciseBinding? = null
    private val binding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStartExerciseBinding.inflate(inflater, container, false)
        binding.ex1.setOnClickListener {
            ExerciseFragment.key=1
            findNavController().navigate(R.id.action_startExerciseFragment_to_exerciseFragment)
        }
        binding.ex2.setOnClickListener {
            ExerciseFragment.key=2
            findNavController().navigate(R.id.action_startExerciseFragment_to_exerciseFragment)
        }
        binding.ex3.setOnClickListener {
            ExerciseFragment.key=3
            findNavController().navigate(R.id.action_startExerciseFragment_to_exerciseFragment)
        }
        binding.ex4.setOnClickListener {
            ExerciseFragment.key=4
            findNavController().navigate(R.id.action_startExerciseFragment_to_exerciseFragment)
        }
        binding.ex5.setOnClickListener {
            ExerciseFragment.key=5
            findNavController().navigate(R.id.action_startExerciseFragment_to_exerciseFragment)
        }
        binding.ex6.setOnClickListener {
            ExerciseFragment.key=6
            findNavController().navigate(R.id.action_startExerciseFragment_to_exerciseFragment)
        }
        binding.ex7.setOnClickListener {
            ExerciseFragment.key=7
            findNavController().navigate(R.id.action_startExerciseFragment_to_exerciseFragment)
        }
        binding.ex8.setOnClickListener {
            ExerciseFragment.key=8
            findNavController().navigate(R.id.action_startExerciseFragment_to_exerciseFragment)
        }
        return binding.root
    }
}