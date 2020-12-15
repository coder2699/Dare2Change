package com.example.inout2020_aimers.Resources

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.inout2020_aimers.R
import kotlinx.android.synthetic.main.fragment_resource_web.*


class ResourceWebFragment : Fragment(R.layout.fragment_resource_web) {

    val args : ResourceWebFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = args.url

        webViewResource.apply {
            webViewClient = WebViewClient()
            loadUrl(url)
        }

    }

}