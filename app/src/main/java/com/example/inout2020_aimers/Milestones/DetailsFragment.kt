package com.example.inout2020_aimers.Milestones

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import com.example.inout2020_aimers.Milestones.Database.Goals
import com.example.inout2020_aimers.databinding.FragmentDetailsBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class DetailsFragment : BottomSheetDialogFragment() {
    companion object {
        fun newInstance(args: Bundle) = DetailsFragment().apply {
            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
    private var _binding: FragmentDetailsBinding? = null
    private val binding
        get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val args = arguments?.getParcelable<Goals>("Goals")

        if (args != null) {
            binding.mainHead.text = args.mainGoal
            binding.goal1.text = "${args.subGoal1} (${args.subGoal1Date})"
            binding.goal2.text = "${args.subGoal2} (${args.subGoal2Date})"
            binding.goal3.text = "${args.subGoal3} (${args.subGoal3Date})"
            if(args.subGoal2==""){
                binding.goal2.visibility=GONE
                binding.two.visibility= GONE
                binding.sw.visibility= GONE
                binding.finishTwo.visibility=VISIBLE
                binding.goal3.visibility=GONE
                binding.three.visibility= GONE
                binding.se2.visibility= GONE
                binding.finalFinish.visibility= GONE
            }
            else if(args.subGoal3==""){
                binding.goal3.visibility=GONE
                binding.three.visibility= GONE
                binding.se2.visibility= GONE
                binding.finalFinish.visibility= GONE
                binding.finishThree.visibility=VISIBLE
            }
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        // this forces the sheet to appear at max height even on landscape
        val behavior = BottomSheetBehavior.from(requireView().parent as View)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}