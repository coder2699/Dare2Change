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
            "Eat That Frog!: 21 Great Ways to Stop Procrastinating and Get More Done in Less Time"
        ),
        Resources(//Done
            "Deep Work: Rules for Focused Success in a Distracted World"
        ),
        Resources(//Done
            "The Obstacle Is the Way: The Timeless Art of Turning Trials into Triumph"
        ),
        Resources(//Done
            "7 Habits Of Highly Effective People"
        ),
        Resources( //Done
            "Ikigai: Japaneses secrete of Long And Happy Life"
        ),
        Resources( //Done
            "No Excuses!: The Power of Self-Discipline for Success in Your Life "
        ),
        Resources(//Done
            "The Power of Habit: Why We Do What We Do in Life and Business "
        ),
        Resources(//Done
            "War Of Art: Break Through the Blocks and Win Your Inner Creative Battles"
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