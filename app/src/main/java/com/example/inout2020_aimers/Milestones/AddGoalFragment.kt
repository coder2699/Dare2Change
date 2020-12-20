package com.example.inout2020_aimers.Milestones

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.inout2020_aimers.Milestones.Database.Goals
import com.example.inout2020_aimers.Milestones.Database.GoalsViewModel
import com.example.inout2020_aimers.R
import com.example.inout2020_aimers.databinding.FragmentAddGoalBinding
import com.example.inout2020_aimers.utils.Snacker
import com.google.android.material.snackbar.Snackbar
import java.text.DateFormatSymbols
import java.time.Month
import java.time.MonthDay
import java.time.Year
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class AddGoalFragment : Fragment() {
    private lateinit var goalsViewModel: GoalsViewModel
    private lateinit var mainDatePicker: DatePickerDialog
    private lateinit var subDate1Picker: DatePickerDialog
    private lateinit var subDate2Picker: DatePickerDialog
    private lateinit var subDate3Picker: DatePickerDialog
    private var _binding: FragmentAddGoalBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddGoalBinding.inflate(inflater, container, false)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        binding.mainGoalDate.setOnClickListener {
            mainDatePicker = setDeadlineDate(binding.mainGoalDate, year, month, day)
            mainDatePicker.show()
        }
        binding.subGoal1Date.setOnClickListener {
            subDate1Picker = setDeadlineDate(binding.subGoal1Date, year, month, day)
            subDate1Picker.show()
        }
        binding.subGoal2Date.setOnClickListener {
            subDate2Picker = setDeadlineDate(binding.subGoal2Date, year, month, day)
            subDate2Picker.show()
        }
        binding.subGoal3Date.setOnClickListener {
            subDate3Picker = setDeadlineDate(binding.subGoal3Date, year, month, day)
            subDate3Picker.show()
        }
        binding.moreOne.setOnClickListener {
            binding.subGoal2TextInput.visibility = VISIBLE
            binding.subGoal2Date.visibility = VISIBLE
            binding.cal2.visibility = VISIBLE
            binding.moreTwo.visibility = VISIBLE
        }
        binding.moreTwo.setOnClickListener {
            binding.subGoal3TextInput.visibility = VISIBLE
            binding.subGoal3Date.visibility = VISIBLE
            binding.cal3.visibility = VISIBLE
        }
        binding.doneBtn.setOnClickListener {
            goalsViewModel = ViewModelProviders.of(this).get(GoalsViewModel::class.java)
            if (binding.mainGoalDate.text.toString() == "" || binding.mainGoalEditText.text.toString() == ""
            ) {
                Snacker(it, "Main Goal cannot be empty").error()
            } else if (binding.subGoal1Date.text.toString() == "" || binding.subGoal1EditText.text.toString() == ""
            ) {
                Snacker(it, "Atleast add 1st Sub-Goal").error()
            } else if ((binding.subGoal2Date.text.toString() == "" || binding.subGoal2EditText.text.toString() == "") &&
                (binding.subGoal3Date.text.toString() != "" || binding.subGoal3EditText.text.toString() != "")
            ) {
                Snacker(it, "2nd Sub-Goal should be added").error()
            } else {
                val goal = Goals(
                    mainGoal = binding.mainGoalEditText.text.toString(),
                    mainGoalDate = binding.mainGoalDate.text.toString(),
                    subGoal1 = binding.subGoal1EditText.text.toString(),
                    subGoal1Date = binding.subGoal1Date.text.toString(),
                    subGoal2 = binding.subGoal2EditText.text.toString(),
                    subGoal2Date = binding.subGoal2Date.text.toString(),
                    subGoal3 = binding.subGoal3EditText.text.toString(),
                    subGoal3Date = binding.subGoal3Date.text.toString(),
                )
                goal.goalId = System.currentTimeMillis()
                goalsViewModel.insert(goal)
                findNavController().navigate(R.id.action_addGoalFragment_to_startFragment)
            }
        }
        return binding.root
    }

    private fun setDeadlineDate(it: EditText, year: Int, month: Int, day: Int): DatePickerDialog {
        return DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { datePicker, year, monthOfYear, dayOfMonth ->
                it.setText("${DateFormatSymbols().months[monthOfYear]} $dayOfMonth, $year")
            }, year, month, day
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}