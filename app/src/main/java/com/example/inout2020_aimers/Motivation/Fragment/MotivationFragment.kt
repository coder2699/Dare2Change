package com.example.inout2020_aimers.Motivation.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.inout2020_aimers.Motivation.Fragment.Adapter.MotivationImageAdapter
import com.example.inout2020_aimers.R
import kotlinx.android.synthetic.main.fragment_motivation.view.*


class MotivationFragment : Fragment(), GalleryImageClickListener {
    private val SPAN_COUNT = 3
    private val imageList = ArrayList<Image>()
    lateinit var galleryAdapter: MotivationImageAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_motivation, container, false)

        galleryAdapter = MotivationImageAdapter(imageList)
        galleryAdapter.listener = this
        // init recyclerview
        view.recyclerView.layoutManager = GridLayoutManager(activity, SPAN_COUNT)
        view.recyclerView.adapter = galleryAdapter
        // load images
        loadImages()
        return view
    }
    private fun loadImages() {
        imageList.add(Image("https://cdn5.vectorstock.com/i/1000x1000/42/99/best-motivational-quote-for-better-life-vector-23004299.jpg"))
        imageList.add(Image("https://i.pinimg.com/736x/33/f0/de/33f0de21efdfc2b5ded6a253f4f5b01d.jpg"))
        imageList.add(Image("https://i.pinimg.com/originals/a6/2a/09/a62a094cbeec5d02eb74f2e7034745e0.jpg"))
        imageList.add(Image("https://i.pinimg.com/originals/6d/65/22/6d6522a0c5386c2e9a70712016fa40df.jpg"))
        imageList.add(Image("https://2.bp.blogspot.com/-ZZ_4Ns0tZIk/XAyT9TgdNpI/AAAAAAAAXoE/cHSUhnQMfY8UUv5TksP5nWhdvgGu6_bjACLcBGAs/s1600/motivational%2Bquotes%2Bwallpaper%2B%25281%2529.jpg"))
        imageList.add(Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_ufmhp_YgVTFsX6i296Z6uO40lS5v65ffrA&usqp=CAU"))
        imageList.add(Image("https://previews.123rf.com/images/hsakakian/hsakakian1809/hsakakian180900008/107364738-inspirational-quotes-you-are-stronger-than-you-know-positive-motivational-inspiration.jpg"))
        imageList.add(Image("https://i.pinimg.com/736x/77/f0/4a/77f04a1382d50450d6a756a7300bf237.jpg"))
        imageList.add(Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTL3u46SQT6wIvYkPNPWOp-GvR82A5tm75ejQ&usqp=CAU"))
        imageList.add(Image("https://media.istockphoto.com/photos/quote-best-inspirational-and-motivational-quotes-and-sayings-about-picture-id924075864"))
        imageList.add(Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRZobFjXjHaqSVbzfBXJfIO9hg2GXWJNwRA-g&usqp=CAU"))
        imageList.add(Image("https://boomsumo.com/wp-content/uploads/2017/05/Positive-quotes-about-motivational-thoughts-If-fails-Sometimes-You-Learn-positive-words-motivational-sayings.jpg"))
        imageList.add(Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSDXoSF4oA4g-t9nRuvQPgxzyjcs-Hi4frnaA&usqp=CAU"))
        imageList.add(Image("https://i.pinimg.com/originals/9c/2a/2d/9c2a2d0dec0fb1cbf02a5cef80a4d6bf.jpg"))
        imageList.add(Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRxeEn-Ys-bxqCvutC1NC_OJfGAmEZ5GJ_joA&usqp=CAU"))
        galleryAdapter.notifyDataSetChanged()
    }

    override fun onClick(position: Int) {
        val bundle = Bundle()
        bundle.putSerializable("images", imageList)
        bundle.putInt("position", position)
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        val galleryFragment = MotivationFullscreenFragment()
        galleryFragment.setArguments(bundle)
        if (fragmentTransaction != null) {
            galleryFragment.show(fragmentTransaction, "gallery")
        }
    }
}