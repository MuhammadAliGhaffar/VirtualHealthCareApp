package com.example.vhaapp.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.vhaapp.R
import com.example.vhaapp.utils.KeyValueStore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookAppointmentFragment : Fragment() {

    private lateinit var doctorNameTextView: TextView
    private lateinit var doctorHospitalTextView: TextView
    private lateinit var patientNameTextView: TextView
    private lateinit var patientGenderTextView: TextView
    private lateinit var edtAppointmentTime: EditText
    private lateinit var edtAppointmentDate: EditText
    private lateinit var edtComment: EditText
    private lateinit var suggestDoctorButton: Button
    private lateinit var suggestDoctorImageView: ImageView
    private lateinit var patientImageView: ImageView

    private val viewModel: BookAppointmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.book_appointment_fragment, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        doctorNameTextView = view.findViewById(R.id.doctorNameTextView)
        doctorHospitalTextView = view.findViewById(R.id.doctorHospitalTextView)
        patientNameTextView = view.findViewById(R.id.patientNameTextView)
        patientGenderTextView = view.findViewById(R.id.patientGenderTextView)
        edtAppointmentTime = view.findViewById(R.id.edtAppointmentTime)
        edtAppointmentDate = view.findViewById(R.id.edtAppointmentDate)
        edtComment = view.findViewById(R.id.edtComment)
        suggestDoctorImageView = view.findViewById(R.id.suggestDoctorImageView)
        patientImageView = view.findViewById(R.id.patientImageView)
        suggestDoctorButton = view.findViewById(R.id.suggestDoctorButton)

        if (KeyValueStore.getPatientDetails().patient_gender.equals("Female")) {
            patientImageView.setImageResource(R.drawable.patient_female)
        } else {
            patientImageView.setImageResource(R.drawable.patient_male)
        }

        patientNameTextView.text =
            KeyValueStore.getPatientDetails().patient_fname + " " + KeyValueStore.getPatientDetails().patient_lname
        patientGenderTextView.text = KeyValueStore.getPatientDetails().patient_gender

        if (arguments != null) {
            if (requireArguments().get("doctor_gender").toString().equals("Female")) {
                suggestDoctorImageView.setImageResource(R.drawable.female_doctor)
            } else {
                suggestDoctorImageView.setImageResource(R.drawable.male_doctor)
            }
            doctorNameTextView.text = requireArguments().get("doctor_fname")
                .toString() + " " + requireArguments().get("doctor_lname").toString()
            doctorHospitalTextView.text = requireArguments().get("current_company").toString()
        }

        suggestDoctorButton.setOnClickListener {
            if (edtAppointmentTime.text.isNotEmpty() && edtAppointmentDate.text.isNotEmpty() && edtComment.text.isNotEmpty()) {
                val progressDialog = ProgressDialog(context)
                progressDialog.setMessage("Booking Appointment, please wait")
                progressDialog.show()

                viewModel.bookAppointment(
                    requireArguments().get("doctor_id").toString(),
                    KeyValueStore.getPatientDetails().patient_id.toString(),
                    edtAppointmentTime.text.toString(),
                    edtAppointmentDate.text.toString(),
                    edtComment.text.toString()
                ) { isSuccessful, message ->
                    if (isSuccessful) {
                        progressDialog.dismiss()
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_bookAppointmentFragment_to_homeFragment)
                    } else {
                        progressDialog.dismiss()
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

    }

}