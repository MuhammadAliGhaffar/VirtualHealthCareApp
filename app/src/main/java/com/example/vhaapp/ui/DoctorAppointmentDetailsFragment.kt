package com.example.vhaapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.vhaapp.R

class DoctorAppointmentDetailsFragment : Fragment() {

    private lateinit var doctorNameTextView: TextView
    private lateinit var doctorSpecializationTextView: TextView
    private lateinit var doctorHospitalTextView: TextView
    private lateinit var areaDoctorTitle: TextView
    private lateinit var areaDoctorDescription: TextView
    private lateinit var suggestDoctorImageView: ImageView
    private lateinit var suggestDoctorButton: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_doctor_appointment_details, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        doctorNameTextView = view.findViewById(R.id.doctorNameTextView)
        doctorSpecializationTextView = view.findViewById(R.id.doctorSpecializationTextView)
        doctorHospitalTextView = view.findViewById(R.id.doctorHospitalTextView)
        areaDoctorTitle = view.findViewById(R.id.areaDoctorTitle)
        areaDoctorDescription = view.findViewById(R.id.areaDoctorDescription)
        suggestDoctorImageView = view.findViewById(R.id.suggestDoctorImageView)
        suggestDoctorButton = view.findViewById(R.id.suggestDoctorButton)

        if (arguments != null) {
            if (requireArguments().get("doctor_gender")?.equals("Female") == true) {
                suggestDoctorImageView.setImageResource(R.drawable.female_doctor)
            } else {
                suggestDoctorImageView.setImageResource(R.drawable.male_doctor)
            }
            doctorNameTextView.text = requireArguments().get("doctor_fname")
                .toString() + " " + requireArguments().get("doctor_lname").toString()
            doctorSpecializationTextView.text =
                requireArguments().get("disease_specialization").toString()
            doctorHospitalTextView.text = requireArguments().get("current_company").toString()
            areaDoctorTitle.text = resources.getString(
                R.string.who_are,
                requireArguments().get("designation").toString()
            )
            areaDoctorDescription.text = requireArguments().get("disease_specialization").toString()
        }

        suggestDoctorButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_doctorAppointmentDetailsFragment_to_bookAppointmentFragment,
                arguments as Bundle
            )
        }
    }

}