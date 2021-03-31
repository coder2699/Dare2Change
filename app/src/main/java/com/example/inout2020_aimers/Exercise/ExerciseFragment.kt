package com.example.inout2020_aimers.Exercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.inout2020_aimers.R
import com.example.inout2020_aimers.databinding.FragmentExerciseBinding

class ExerciseFragment : Fragment() {
    companion object{
        var key:Int = -1
    }
    private var _binding: FragmentExerciseBinding? = null
    private val binding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentExerciseBinding.inflate(inflater, container, false)
        if(key == 1){
            binding.demo.setImageResource(R.drawable.ex_aa)
            binding.head.text=resources.getString(R.string.exHead1)
            binding.description.text=resources.getString(R.string.desc1)
        }
        if(key == 2){
            binding.demo.setImageResource(R.drawable.tratak)
            binding.head.text=resources.getString(R.string.exHead2)
            binding.description.text=resources.getString(R.string.desc2)
        }
        if(key == 3){
            binding.demo.setImageResource(R.drawable.vrik)
            binding.head.text=resources.getString(R.string.exHead3)
            binding.description.text=resources.getString(R.string.desc3)
        }
        if(key == 4){
            binding.demo.setImageResource(R.drawable.tada)
            binding.head.text=resources.getString(R.string.exHead4)
            binding.description.text=resources.getString(R.string.desc4)
        }
        if(key == 5){
            binding.demo.setImageResource(R.drawable.natrajan)
            binding.head.text=resources.getString(R.string.exHead5)
            binding.description.text=resources.getString(R.string.desc5)
        }
        if(key == 6){
            binding.demo.setImageResource(R.drawable.paschi)
            binding.head.text=resources.getString(R.string.exHead6)
            binding.description.text=resources.getString(R.string.desc6)
        }
        if(key == 7){
            binding.demo.setImageResource(R.drawable.ustra)
            binding.head.text=resources.getString(R.string.exHead7)
            binding.description.text=resources.getString(R.string.desc7)
        }
        if(key == 8){
            binding.demo.setImageResource(R.drawable.virasana)
            binding.head.text=resources.getString(R.string.exHead8)
            binding.description.text=resources.getString(R.string.desc8)
        }
        return binding.root
    }
}