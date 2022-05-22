package com.example.vhaapp.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vhaapp.R
import com.example.vhaapp.model.Doctor
import com.example.vhaapp.ui.adapter.DoctorsAdapter
import com.example.vhaapp.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OurDoctorsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var doctorAdapter: DoctorsAdapter
    private lateinit var imgBack: ImageView

    private val viewModel: OurDoctorViewModel by viewModels()

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

        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Please wait - fetching doctors")
        progressDialog.show()
        viewModel.getDoctorsList { isPatientAdded, message, doctorsList ->
            if (isPatientAdded) {
                progressDialog.dismiss()
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                doctorAdapter.setDoctorList(doctorsList)
            } else {
                progressDialog.dismiss()
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
        recyclerView.adapter = doctorAdapter
        doctorAdapter.onItemClick = { doctor: Doctor ->
            Utils.toast(requireContext(),doctor.doctorFirstName)
            findNavController().navigate(R.id.action_ourDoctorsFragment_to_viewDoctorDetailsFragment)
        }

        imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}