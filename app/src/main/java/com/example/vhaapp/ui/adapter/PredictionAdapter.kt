package com.example.vhaapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vhaapp.R
import com.example.vhaapp.model.Prediction
import com.example.vhaapp.utils.KeyValueStore

class PredictionAdapter : RecyclerView.Adapter<PredictionAdapter.ViewHolder>() {
    private var list = mutableListOf<Prediction>()

    /**
     * onClickItem RelativeLayout
     */
    var onItemClick: ((Prediction) -> Unit)? = null

    fun setPredictionList(list: List<Prediction>) {
        this.list = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.prediction_item_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val predictionModel = list[position]

        holder.patientNameTextView.text =
            holder.itemView.resources.getString(
                R.string.patient_name,
                KeyValueStore.getPatientDetails().patient_fname + " " + KeyValueStore.getPatientDetails().patient_lname
            )
        holder.diseaseTextView.text =
            holder.itemView.resources.getString(
                R.string.disease_name,
                predictionModel.disease_name
            )
        holder.predictionDateTextView.text =
            holder.itemView.resources.getString(
                R.string.prediction_date,
                predictionModel.prediction_date
            )

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val patientNameTextView: TextView = itemView.findViewById(R.id.patientNameTextView)
        val diseaseTextView: TextView = itemView.findViewById(R.id.diseaseTextView)
        val predictionDateTextView: TextView = itemView.findViewById(R.id.predictionDateTextView)
    }
}