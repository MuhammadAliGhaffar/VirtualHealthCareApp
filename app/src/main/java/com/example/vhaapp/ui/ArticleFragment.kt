package com.example.vhaapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.vhaapp.R

class ArticleFragment : Fragment() {

    private lateinit var imgBack: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_article, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View){
        imgBack = view.findViewById(R.id.imgBack)

        imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}