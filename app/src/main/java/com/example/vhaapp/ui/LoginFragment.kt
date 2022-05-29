package com.example.vhaapp.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.vhaapp.R
import com.example.vhaapp.utils.KeyValueStore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var edtUsername: TextView
    private lateinit var edtPassword: TextView
    private lateinit var txtForgetPassword: TextView
    private lateinit var txtSignUp: TextView
    private lateinit var btnSignIn: Button

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_fragment, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        edtUsername = view.findViewById(R.id.edtUsername)
        edtPassword = view.findViewById(R.id.edtPassword)
        txtForgetPassword = view.findViewById(R.id.txtForgetPassword)
        txtSignUp = view.findViewById(R.id.txtSignUp)
        btnSignIn = view.findViewById(R.id.btnSignIn)


        if (KeyValueStore.getPatientDetails().patient_username != null &&
            KeyValueStore.getPatientDetails().patient_email != null
        ) {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        } else {
            goToHomeFragment()
            goToRegistrationFragment()
        }

    }

    private fun goToHomeFragment() {
        btnSignIn.setOnClickListener {
            if (edtUsername.text.isNotEmpty() &&
                edtPassword.text.isNotEmpty()
            ){
                val progressDialog = ProgressDialog(context)
                progressDialog.setMessage("Logging In, please wait")
                progressDialog.show()

                viewModel.loginPatient(
                    edtUsername.text.toString(),
                    edtPassword.text.toString()
                ) { isPatientAdded, message ->
                    if (isPatientAdded) {
                        progressDialog.dismiss()
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    } else {
                        progressDialog.dismiss()
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
                }

            }else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToRegistrationFragment() {
        txtSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

}