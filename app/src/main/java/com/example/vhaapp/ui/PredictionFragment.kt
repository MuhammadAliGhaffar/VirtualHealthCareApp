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
import com.example.vhaapp.ui.adapter.DoctorsAdapter
import com.example.vhaapp.ui.adapter.PredictionAdapter
import com.example.vhaapp.utils.KeyValueStore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PredictionFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var predictionAdapter: PredictionAdapter
    private lateinit var imgBack: ImageView

    private val viewModel: PredictionViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.prediction_fragment, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View){
        recyclerView = view.findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)
        predictionAdapter = PredictionAdapter()
        imgBack = view.findViewById(R.id.imgBack)

        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Please wait - fetching predictions")
        progressDialog.show()
        viewModel.getPredictionList(KeyValueStore.getPatientDetails().patient_id.toString()) { isPatientAdded, message, predictionList ->
            if (isPatientAdded) {
                progressDialog.dismiss()
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                predictionAdapter.setPredictionList(predictionList)
            } else {
                progressDialog.dismiss()
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
        recyclerView.adapter = predictionAdapter


        imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}