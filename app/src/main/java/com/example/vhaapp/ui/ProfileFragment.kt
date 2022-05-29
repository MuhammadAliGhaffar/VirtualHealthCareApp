package com.example.vhaapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.example.vhaapp.R
import com.example.vhaapp.utils.KeyValueStore


class ProfileFragment : Fragment() {

    private lateinit var edtUsername: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtFirstName: EditText
    private lateinit var edtLastName: EditText
    private lateinit var edtDateOfBirth: EditText
    private lateinit var edtAge: EditText
    private lateinit var edtWeight: EditText
    private lateinit var edtHeight: EditText
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var btnUpdateProfile: Button
    private lateinit var imgBack: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        edtUsername = view.findViewById(R.id.edtUsername)
        edtEmail = view.findViewById(R.id.edtEmail)
        edtFirstName = view.findViewById(R.id.edtFirstName)
        edtLastName = view.findViewById(R.id.edtLastName)
        edtDateOfBirth = view.findViewById(R.id.edtDateOfBirth)
        edtAge = view.findViewById(R.id.edtAge)
        edtWeight = view.findViewById(R.id.edtWeight)
        edtHeight = view.findViewById(R.id.edtHeight)
        radioGroupGender = view.findViewById(R.id.radioGroupGender)
        btnUpdateProfile = view.findViewById(R.id.btnUpdateProfile)
        imgBack = view.findViewById(R.id.imgBack)

        getPatientDetailsInViews()

        imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun getPatientDetailsInViews() {
        if (!KeyValueStore.getPatientDetails().patient_username.equals("") &&
            !KeyValueStore.getPatientDetails().patient_email.equals("")
        ) {
            edtUsername.setText(KeyValueStore.getPatientDetails().patient_username)
            edtEmail.setText(KeyValueStore.getPatientDetails().patient_email)
            edtFirstName.setText(KeyValueStore.getPatientDetails().patient_fname)
            edtLastName.setText(KeyValueStore.getPatientDetails().patient_lname)
            edtDateOfBirth.setText(KeyValueStore.getPatientDetails().patient_dob)
            edtAge.setText(KeyValueStore.getPatientDetails().patient_age)
            edtWeight.setText(KeyValueStore.getPatientDetails().patient_weight)
            edtHeight.setText(KeyValueStore.getPatientDetails().patient_height)
            view?.findViewById<RadioButton>(radioGroupGender.checkedRadioButtonId)
                ?.text = KeyValueStore.getPatientDetails().patient_height
        } else {
            //do nothing empty fields will be shown
            Toast.makeText(context, "Error while fetching error", Toast.LENGTH_SHORT).show()
        }
    }
}