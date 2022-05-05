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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private lateinit var edtUsername: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var edtFirstName: EditText
    private lateinit var edtLastName: EditText
    private lateinit var edtDateOfBirth: EditText
    private lateinit var edtAge: EditText
    private lateinit var edtWeight: EditText
    private lateinit var edtHeight: EditText
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var btnSignUp: Button
    private val viewModel: RegistrationViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.registration_fragment, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        edtUsername = view.findViewById(R.id.edtUsername)
        edtEmail = view.findViewById(R.id.edtEmail)
        edtPassword = view.findViewById(R.id.edtPassword)
        edtFirstName = view.findViewById(R.id.edtFirstName)
        edtLastName = view.findViewById(R.id.edtLastName)
        edtDateOfBirth = view.findViewById(R.id.edtDateOfBirth)
        edtAge = view.findViewById(R.id.edtAge)
        edtWeight = view.findViewById(R.id.edtWeight)
        edtHeight = view.findViewById(R.id.edtHeight)
        radioGroupGender = view.findViewById(R.id.radioGroupGender)
        btnSignUp = view.findViewById(R.id.btnSignUp)
        //TODO bug unchecked issue
        val value =
            view.findViewById<RadioButton>(radioGroupGender.checkedRadioButtonId).text.toString()

        btnSignUp.setOnClickListener {
            if (edtEmail.text.isNotEmpty() &&
                edtEmail.text.isNotEmpty() &&
                edtEmail.text.isNotEmpty() &&
                edtEmail.text.isNotEmpty() &&
                edtEmail.text.isNotEmpty() &&
                edtEmail.text.isNotEmpty() &&
                edtEmail.text.isNotEmpty() &&
                edtEmail.text.isNotEmpty() &&
                value != null) {

                val progressDialog = ProgressDialog(context)
                progressDialog.setMessage("Loading, please wait")
                progressDialog.show()

                val patientUsername =
                    "Pat-" + edtUsername.text + "-" + (100..999).shuffled().last().toString()
                viewModel.registerPatient(
                    patientUsername,
                    edtPassword.text.toString(),
                    edtEmail.text.toString(),
                    edtFirstName.text.toString(),
                    edtLastName.text.toString(),
                    edtDateOfBirth.text.toString(),
                    edtWeight.text.toString(),
                    edtHeight.text.toString(),
                    value,
                    edtAge.text.toString()
                ) { isPatientAdded, message ->
                    if (isPatientAdded) {
                        progressDialog.dismiss()
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
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