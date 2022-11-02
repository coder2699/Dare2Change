package com.example.inout2020_aimers.WeeklyPlanner

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inout2020_aimers.R
import com.example.inout2020_aimers.WeeklyPlanner.Adapter.DayAdapter
import com.example.inout2020_aimers.WeeklyPlanner.Database.Day
import com.example.inout2020_aimers.WeeklyPlanner.Database.DayViewModel
import com.example.inout2020_aimers.databinding.FragmentWeekBinding

class WeekFragment : Fragment() {
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var dayViewModel: DayViewModel
    private var _binding: FragmentWeekBinding? = null
    lateinit var newList : ArrayList<Day>
    private val binding
        get() = _binding!!
    private lateinit var dayAdapter: DayAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWeekBinding.inflate(inflater, container, false)
        dayViewModel = ViewModelProviders.of(this).get(DayViewModel::class.java)
        dayAdapter = DayAdapter(dayViewModel, binding.rv.rootView)
        sharedPreferences = context?.getSharedPreferences("FIRST_OPEN", Context.MODE_PRIVATE)!!
        binding.rv.adapter = dayAdapter
        binding.toolbarDashboard.inflateMenu(R.menu.reset_btn)
        binding.toolbarDashboard.setOnMenuItemClickListener {
            onOptionsItemSelected(it)
        }
        binding.rv.layoutManager = LinearLayoutManager(context)
        if (sharedPreferences.getInt("FIRST_OPEN",0) == 0) {
            val day0 = Day(
                dayTask = "",
                dayName = "MONDAY",
                id = 0
            )
            val day1 = Day(
                dayTask = "",
                dayName = "TUESDAY",
                id = 1
            )
            val day2 = Day(
                dayTask = "",
                dayName = "WEDNESDAY",
                id = 2
            )
            val day3 = Day(
                dayTask = "",
                dayName = "THURSDAY",
                id = 3
            )
            val day4 = Day(
                dayTask = "",
                dayName = "FRIDAY",
                id = 4
            )
            val day5 = Day(
                dayTask = "",
                dayName = "SATURDAY",
                id = 5
            )
            val day6 = Day(
                dayTask = "",
                dayName = "SUNDAY",
                id = 6
            )
            dayViewModel.insert(day0)
            dayViewModel.insert(day1)
            dayViewModel.insert(day2)
            dayViewModel.insert(day3)
            dayViewModel.insert(day4)
            dayViewModel.insert(day5)
            dayViewModel.insert(day6)
        }
        val editor = sharedPreferences.edit()
        editor?.putInt("FIRST_OPEN",1)
        editor?.apply()

        dayViewModel.allDays.observe(viewLifecycleOwner, Observer { list ->
            newList = list as ArrayList<Day>
            dayAdapter.submitList(list)
            dayAdapter.dayList = list as ArrayList<Day>
        })
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.reset->{
                dayViewModel.getNewLivedata(newList)
                onResume()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        dayViewModel.allDays.observe(viewLifecycleOwner, Observer { list ->
            newList = list as ArrayList<Day>
            dayAdapter.submitList(list)
            dayAdapter.dayList = list as ArrayList<Day>
        })
        super.onResume()
    }
}