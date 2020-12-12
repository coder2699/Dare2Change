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
            "50 Ways to Increase Productivity and Achieve More in Less Time"
        ),
        Resources(//Done
            "The 80-20 rule - Pareto Principle"
        ),
        Resources(//Done
            "Eisenhower's Urgent/Important Principle\n" +
                    "Using Time Effectively, Not Just Efficiently"
        ),
        Resources(//Done
            "10 smart productivity techniques you should know about"
        ),
        Resources(//Done
            "Follow @imshivkhera\n" +
                    "- Mr. Shiv Khera is an Author, Educator, Business Consultant and successful Entrepreneur, he is a much sought-after speaker."
        ),
        Resources(//Done
            "Follow @RobinSharma\n" +
                    "- One of world’s top leadership experts + Humanitarian + author of the #1 worldwide bestseller #The5amClub"
        ),
        Resources(//Done
            "Follow @garyvee\n" +
                    "- entrepreneur, investor, author, speaker"
        ),
        Resources(//Done
            "Follow @GrantCardone\n" +
                    "- Star of Undercover Billionaire, CEO CardoneCapital \$2.2B AUM, Author & Founder of 10X Movement."
        ),
        Resources(//Done
            "Follow @BrianTracy\n" +
                    "- Professional Speaker, Author, Success Expert, CEO of BrianTracy International & 14 Step Goal-Setting Guide"
        ),
        Resources(//Done
            "Follow @OfficialJimRohn\n" +
                    ", the man many consider to be America's Foremost Business Philosopher, shared his success philosophies and principles for over 46 years."
        ),
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