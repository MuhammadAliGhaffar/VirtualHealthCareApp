package com.example.vhaapp.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
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

          setupRightMenu()
          setupNavigationDrawer()
     }


     private fun setupRightMenu() {
          //Inflate right menu in Toolbar
          toolbar.inflateMenu(R.menu.main_menu)
          toolbar.setOnMenuItemClickListener {
               when (it.itemId) {
                    R.id.menuContactUs -> {
                         Toast.makeText(activity, "Contact Us", Toast.LENGTH_SHORT).show()
                         true
                    }
                    R.id.menuTermsAndCondition -> {
                         Toast.makeText(activity, "Terms and Conditions", Toast.LENGTH_SHORT).show()
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
                    R.id.menuAbout -> {
                    }
                    R.id.menuContact -> {
                    }
               }
               menuItem.isChecked = true
               mainDrawer.close()
               true
          }
     }

}