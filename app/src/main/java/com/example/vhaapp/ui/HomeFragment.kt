package com.example.vhaapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.vhaapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView

class HomeFragment : Fragment() {

     private lateinit var toolbar: Toolbar
     private lateinit var mainDrawer: DrawerLayout
     private lateinit var navigationView: NavigationView
     private lateinit var bottomNavigation: BottomNavigationView
     private lateinit var mainHomeLayout:RelativeLayout

     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setHasOptionsMenu(true)
     }

     override fun onCreateView(
          inflater: LayoutInflater, container: ViewGroup?,
          savedInstanceState: Bundle?
     ): View? {
          val view = inflater.inflate(R.layout.home_fragment, container, false)
          initView(view)
          return view
     }

     override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
          inflater.inflate(R.menu.main_menu, menu)
     }

     private fun initView(view: View) {
          toolbar = view.findViewById(R.id.toolbar)
          mainDrawer = view.findViewById(R.id.mainDrawer)
          navigationView = view.findViewById(R.id.navigationView)
          mainHomeLayout = view.findViewById(R.id.mainHomeLayout)

          mainHomeLayout.findViewById<CardView>(R.id.emergencyCardView).setOnClickListener {
               startActivity(Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + "021111111134")))
          }
          mainHomeLayout.findViewById<CardView>(R.id.ourAgentCardView).setOnClickListener {
               findNavController().navigate(R.id.action_homeFragment_to_chatFragment)
          }
          mainHomeLayout.findViewById<CardView>(R.id.ourDoctorsCardView).setOnClickListener {
               findNavController().navigate(R.id.action_homeFragment_to_ourDoctorsFragment)
          }
          mainHomeLayout.findViewById<CardView>(R.id.nearestHospitalCardView).setOnClickListener {
               findNavController().navigate(R.id.action_homeFragment_to_nearestHospitalsFragment)
          }
          mainHomeLayout.findViewById<CardView>(R.id.articlesCardView).setOnClickListener {  }
          mainHomeLayout.findViewById<CardView>(R.id.predictionCardView).setOnClickListener {  }
          mainHomeLayout.findViewById<CardView>(R.id.appointmentsCardView).setOnClickListener {  }

          setupRightMenu()
          setupNavigationDrawer()
     }


     private fun setupRightMenu() {
          //Inflate right menu in Toolbar
          toolbar.inflateMenu(R.menu.main_menu)
          toolbar.setOnMenuItemClickListener {
               when (it.itemId) {
                    R.id.menuLogout -> {
                         Toast.makeText(activity, "Logout", Toast.LENGTH_SHORT).show()
                         true
                    }
                    else -> false
               }
          }
     }

     private fun setupNavigationDrawer() {
          // Left Hamburger onClick to open side drawer
          toolbar.setNavigationOnClickListener {
               mainDrawer.open()
          }

          // Left Hamburger menu item click listener and close functionality
          navigationView.setNavigationItemSelectedListener { menuItem ->
               // Handle menu item selected
               when (menuItem.itemId) {
                    R.id.menuArticle -> {
                    }
                    R.id.menuDoctors -> {
                         findNavController().navigate(R.id.action_homeFragment_to_ourDoctorsFragment)
                    }
               }
               menuItem.isChecked = true
               mainDrawer.close()
               true
          }
     }

}