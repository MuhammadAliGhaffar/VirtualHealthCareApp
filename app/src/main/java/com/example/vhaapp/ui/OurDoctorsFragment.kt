package com.example.vhaapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vhaapp.R
import com.example.vhaapp.model.Doctor
import com.example.vhaapp.ui.adapter.DoctorsAdapter


class OurDoctorsFragment : Fragment() {
     private lateinit var recyclerView: RecyclerView
     private lateinit var doctorAdapter: DoctorsAdapter
     private lateinit var imgBack: ImageView

     override fun onCreateView(
          inflater: LayoutInflater, container: ViewGroup?,
          savedInstanceState: Bundle?
     ): View? {
          // Inflate the layout for this fragment
          val view = inflater.inflate(R.layout.fragment_our_doctors, container, false)
          initView(view)
          return view
     }

     private fun initView(view: View) {
          recyclerView = view.findViewById(R.id.recyclerview)
          recyclerView.layoutManager = LinearLayoutManager(context)
          doctorAdapter = DoctorsAdapter()
          imgBack = view.findViewById(R.id.imgBack)



          val doctorList: List<Doctor> =
               listOf(Doctor(R.drawable.male_doctor,"Ali","Ali","Ali"),
                    Doctor(R.drawable.male_doctor,"Ali","Ali","Ali"),
                    Doctor(R.drawable.male_doctor,"Ali","Ali","Ali"),
                    Doctor(R.drawable.male_doctor,"Ali","Ali","Ali"),
                    Doctor(R.drawable.male_doctor,"Ali","Ali","Ali"),
                    Doctor(R.drawable.male_doctor,"Ali","Ali","Ali"),
                    Doctor(R.drawable.male_doctor,"Ali","Ali","Ali"),
                    Doctor(R.drawable.male_doctor,"Ali","Ali","Ali"),
                    Doctor(R.drawable.male_doctor,"Ali","Ali","Ali"),
                    Doctor(R.drawable.male_doctor,"Ali","Ali","Ali"))
          doctorAdapter.setDoctorList(doctorList)

          recyclerView.adapter = doctorAdapter

          imgBack.setOnClickListener {
               findNavController().navigate(R.id.action_ourDoctorsFragment_to_homeFragment)
          }
     }
}