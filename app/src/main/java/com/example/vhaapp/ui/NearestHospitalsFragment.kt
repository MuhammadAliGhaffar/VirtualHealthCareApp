package com.example.vhaapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.vhaapp.R

class NearestHospitalsFragment : Fragment() {

    private lateinit var webViewMap:WebView
    private lateinit var imgBack: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_nearest_hospitals, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        webViewMap = view.findViewById(R.id.webViewMap)
        imgBack = view.findViewById(R.id.imgBack)

        webViewMap.webViewClient = WebViewClient()
        webViewMap.loadUrl("https://serene-mount-rainier-21655.herokuapp.com/")
        webViewMap.settings.javaScriptEnabled = true
        webViewMap.settings.setSupportZoom(true)

        imgBack.setOnClickListener {
            if (webViewMap.canGoBack())
                webViewMap.goBack()
            else
                findNavController().navigateUp()
        }
    }

}