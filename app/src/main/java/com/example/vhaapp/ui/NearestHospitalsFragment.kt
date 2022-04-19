package com.example.vhaapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vhaapp.R
import com.mapbox.maps.MapView
import com.mapbox.maps.Style

class NearestHospitalsFragment : Fragment() {

    var mapView: MapView? = null

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
        mapView = view.findViewById(R.id.mapView)
        mapView?.getMapboxMap()?.loadStyleUri(Style.MAPBOX_STREETS)

    }
}