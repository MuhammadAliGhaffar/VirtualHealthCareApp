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
import com.example.vhaapp.ui.adapter.AppointmentAdapter
import com.example.vhaapp.ui.adapter.DoctorsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppointmentsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var appointmentAdapter: AppointmentAdapter
    private lateinit var imgBack: ImageView

    private val viewModel: AppointmentsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.appointments_fragment, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)
        appointmentAdapter = AppointmentAdapter()
        imgBack = view.findViewById(R.id.imgBack)

        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Please wait - fetching appointments")
        progressDialog.show()
        viewModel.getAppointmentList { isSuccessful, message, appointmentList ->
            if (isSuccessful) {
                progressDialog.dismiss()
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                appointmentAdapter.setAppointmentList(appointmentList)
            } else {
                progressDialog.dismiss()
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
        recyclerView.adapter = appointmentAdapter

        imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}