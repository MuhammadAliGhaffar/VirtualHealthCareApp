package com.example.vhaapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.vhaapp.R
import com.example.vhaapp.utils.KeyValueStore
import com.google.android.material.navigation.NavigationView

class HomeFragment : Fragment() {

    private lateinit var toolbar: Toolbar
    private lateinit var mainDrawer: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var mainHomeLayout: RelativeLayout

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
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "021111111134")))
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
        mainHomeLayout.findViewById<CardView>(R.id.articlesCardView).setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_articleFragment)
        }
        mainHomeLayout.findViewById<CardView>(R.id.predictionCardView).setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_predictionFragment)
        }
        mainHomeLayout.findViewById<CardView>(R.id.appointmentsCardView).setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_appointmentsFragment)
        }

        setupRightMenu()
        setupNavigationDrawer()
    }


    private fun setupRightMenu() {
        //Inflate right menu in Toolbar
        toolbar.inflateMenu(R.menu.main_menu)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menuLogout -> {
                    KeyValueStore.clearPref()
                    findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
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

        // Left Hamburger Header views
        navigationView.getHeaderView(0).findViewById<TextView>(R.id.textViewUsername)
            .setText(KeyValueStore.getPatientDetails().patient_fname)
        if (KeyValueStore.getPatientDetails().patient_gender.equals("Female")) {
            navigationView.getHeaderView(0).findViewById<ImageView>(R.id.imageViewProfile)
                .setImageDrawable(resources.getDrawable(R.drawable.patient_female))
        } else {
            navigationView.getHeaderView(0).findViewById<ImageView>(R.id.imageViewProfile)
                .setImageDrawable(resources.getDrawable(R.drawable.patient_male))
        }
        navigationView.getHeaderView(0).findViewById<TextView>(R.id.buttonEditProfile)
            .setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
            }

        // Left Hamburger menu item click listener and close functionality
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            when (menuItem.itemId) {
                R.id.menuArticle -> {
                    findNavController().navigate(R.id.action_homeFragment_to_articleFragment)
                }
                R.id.menuAppointments -> {
                    findNavController().navigate(R.id.action_homeFragment_to_appointmentsFragment)
                }
                R.id.menuPrediction -> {
                    findNavController().navigate(R.id.action_homeFragment_to_predictionFragment)
                }
                R.id.menuNearestHospital -> {
                    findNavController().navigate(R.id.action_homeFragment_to_nearestHospitalsFragment)
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