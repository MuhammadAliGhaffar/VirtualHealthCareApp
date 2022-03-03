package com.example.vhaapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.vhaapp.R

class LoginFragment : Fragment() {

     private lateinit var txtForgetPassword: TextView
     private lateinit var txtSignUp: TextView
     private lateinit var btnSignIn: Button

     override fun onCreateView(
          inflater: LayoutInflater, container: ViewGroup?,
          savedInstanceState: Bundle?
     ): View? {
          val view = inflater.inflate(R.layout.login_fragment, container, false)
          initView(view)
          return view
     }

     private fun initView(view: View) {
          txtForgetPassword = view.findViewById(R.id.txtForgetPassword)
          txtSignUp = view.findViewById(R.id.txtSignUp)
          btnSignIn = view.findViewById(R.id.btnSignIn)

          goToHomeFragment()
          goToRegistrationFragment()
     }

     private fun goToHomeFragment() {
          btnSignIn.setOnClickListener {
               findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
          }
     }

     private fun goToRegistrationFragment() {
          txtSignUp.setOnClickListener {
               findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
          }
     }

}