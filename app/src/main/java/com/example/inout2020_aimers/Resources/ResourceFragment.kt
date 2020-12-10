package com.example.inout2020_aimers.Resources

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inout2020_aimers.R
import com.example.inout2020_aimers.Resources.Adapter.ResourceAdapter
import kotlinx.android.synthetic.main.fragment_resource.*


class ResourceFragment : Fragment() {

    var ResourceList = mutableListOf(
        Resources(//Done
            "7 Habits Of Highly Effective People"
        ),
        Resources( //Done
            "Ikigai: Japaneses secrete of Long An d Happy Life"
        ),
        Resources( //Done
            "No Excuses!: The Power of Self-Discipline for Success in Your Life "
        ),
        Resources(//Done
            "The Power of Habit: Why We Do What We Do in Life and Business "
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resource, container, false)
    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RrecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter =
                ResourceAdapter(ResourceList)
        }
    }
}