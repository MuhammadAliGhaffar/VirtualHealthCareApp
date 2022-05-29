package com.example.vhaapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.vhaapp.R

class DoctorAppointmentDetailsFragment : Fragment() {

    private lateinit var doctorNameTextView:TextView
    private lateinit var doctorSpecializationTextView:TextView
    private lateinit var doctorHospitalTextView:TextView
    private lateinit var areaDoctorTitle:TextView
    private lateinit var areaDoctorDescription:TextView
    private lateinit var day1TextView:TextView
    private lateinit var in1TextView:TextView
    private lateinit var out1TextView:TextView
    private lateinit var day2TextView:TextView
    private lateinit var in2TextView:TextView
    private lateinit var out2TextView:TextView
    private lateinit var suggestDoctorButton:Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_appointment_details, container, false)
    }

}