package com.example.inout2020_aimers.Resources

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inout2020_aimers.R
import com.example.inout2020_aimers.Resources.Adapter.ResourceAdapter
import kotlinx.android.synthetic.main.fragment_resource.*


class ResourceFragment : Fragment() {

    var ResourceList = mutableListOf(
        Resources(//Done
            "50 Ways to Increase Productivity and Achieve More in Less Time",
            "https://www.lifehack.org/articles/featured/50-ways-to-increase-your-productivity.html"
        ),
        Resources(//Done
            "The 80-20 rule - Pareto Principle",
            "https://www.investopedia.com/terms/1/80-20-rule.asp#:~:text=The%2080%2D20%20rule%2C%20also,and%20make%20them%20the%20priority."
        ),
        Resources(//Done
            "Eisenhower's Urgent/Important Principle\n" +
                    "Using Time Effectively, Not Just Efficiently",
            "https://www.mindtools.com/pages/article/newHTE_91.htm#:~:text=Eisenhower%2C%20who%20was%20quoting%20Dr,organized%20his%20workload%20and%20priorities."
        ),
        Resources(//Done
            "10 smart productivity techniques you should know about",
            "https://memory.ai/timely-blog/productivity-techniques"
        ),
        Resources(//Done
            "Follow @imshivkhera\n" +
                    "- Mr. Shiv Khera is an Author, Educator, Business Consultant and successful Entrepreneur, he is a much sought-after speaker.",
            "https://twitter.com/imshivkhera"
        ),
        Resources(//Done
            "Follow @RobinSharma\n" +
                    "- One of world’s top leadership experts + Humanitarian + author of the #1 worldwide bestseller #The5amClub",
            "https://twitter.com/RobinSharma"
        ),
        Resources(//Done
            "Follow @garyvee\n" +
                    "- entrepreneur, investor, author, speaker",
            "https://twitter.com/garyvee"
        ),
        Resources(//Done
            "Follow @GrantCardone\n" +
                    "- Star of Undercover Billionaire, CEO CardoneCapital \$2.2B AUM, Author & Founder of 10X Movement.",
            "https://twitter.com/GrantCardone"
        ),
        Resources(//Done
            "Follow @BrianTracy\n" +
                    "- Professional Speaker, Author, Success Expert, CEO of BrianTracy International & 14 Step Goal-Setting Guide",
            "https://twitter.com/BrianTracy"
        ),
        Resources(//Done
            "Follow @OfficialJimRohn\n" +
                    ", the man many consider to be America's Foremost Business Philosopher, shared his success philosophies and principles for over 46 years.",
            "https://twitter.com/OfficialJimRohn"
        ),
        Resources(//Done
            "Eat That Frog!: 21 Great Ways to Stop Procrastinating and Get More Done in Less Time",
            "https://www.amazon.in/Eat-That-Frog-Great-Procrastinating/dp/152309513X/ref=sr_1_2?adgrpid=59632320620&dchild=1&ext_vrnc=hi&gclid=CjwKCAiAq8f-BRBtEiwAGr3DgUe_5yn_l4FRmHQjRToCKId-rSBzrDKxz7j4zieQ9dJ-3zQyjuKb1xoCUjIQAvD_BwE&hvadid=294145717093&hvdev=c&hvlocphy=9303891&hvnetw=g&hvqmt=b&hvrand=1567984751751470638&hvtargid=kwd-354485210287&hydadcr=1105_1742571&keywords=eat+that+frog+book&qid=1607671678&sr=8-2&tag=googinhydr1-21"
        ),
        Resources(//Done
            "Deep Work: Rules for Focused Success in a Distracted World",
            "https://www.amazon.in/Deep-Work-Focused-Success-Distracted/dp/0349413681/ref=pd_bxgy_img_2/262-8855794-8674807?_encoding=UTF8&pd_rd_i=0349413681&pd_rd_r=b85308d3-8c26-415e-97fc-441b51e278ca&pd_rd_w=n51N4&pd_rd_wg=mzOYr&pf_rd_p=3e9f62d7-4196-4823-b41a-9bba671558ea&pf_rd_r=CZ83Z1PG380VJ0BAZB8K&psc=1&refRID=CZ83Z1PG380VJ0BAZB8K"
        ),
        Resources(//Done
            "The Obstacle Is the Way: The Timeless Art of Turning Trials into Triumph",
            "https://www.amazon.in/Obstacle-Way-Timeless-Turning-Triumph/dp/B076YH9WKW/ref=sr_1_1?dchild=1&keywords=The+Obstacle+Is+the+Way+The+Timeless+Art+of+Turning+Trials+into+Triumph&qid=1607671849&sr=8-1"
        ),
        Resources(//Done
            "7 Habits Of Highly Effective People",
            "https://www.amazon.com/Habits-Highly-Effective-People-Powerful/dp/1451639619"
        ),
        Resources( //Done
            "Ikigai: Japaneses secrete of Long And Happy Life",
            "https://www.amazon.com/Ikigai-Japanese-Secret-Long-Happy/dp/0143130722/ref=sr_1_1?crid=3JM46ZTZ9YDE1&dchild=1&keywords=ikigai&qid=1607062250&s=books&sprefix=iki%2Cstripbooks-intl-ship%2C458&sr=1-1"
        ),
        Resources( //Done
            "No Excuses!: The Power of Self-Discipline for Success in Your Life ",
            "https://www.amazon.com/dp/B003MSCSHA?plink=7BrdXQRMieA8KJ2M&ref=adblp13nvvxx_0_5_ti"
        ),
        Resources(//Done
            "The Power of Habit: Why We Do What We Do in Life and Business ",
            "https://www.amazon.com/dp/B007EJSMC8?plink=pBUzaD2ZBsvmxENi&ref=adblp13nvvxx_0_6_im"
        ),
        Resources(//Done
            "War Of Art: Break Through the Blocks and Win Your Inner Creative Battles",
            "https://www.amazon.in/War-Art-Through-Creative-Battles/dp/1936891026/ref=sr_1_1?dchild=1&keywords=The+War+of+Art_+Break+Through+the+Blocks+and+Win+Your+Inner+Creative+Battles&qid=1607671891&sr=8-1"
        )
    )
    lateinit var resourceAdapter: ResourceAdapter

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

        resourceAdapter = ResourceAdapter(ResourceList)

        RrecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = resourceAdapter
        }

        resourceAdapter.setOnItemClickListner {

            val bundle = Bundle().apply {
                putString("url",it)
            }

            findNavController().navigate(R.id.action_resourceFragment_to_resourceWebFragment, bundle)

        }



    }
}