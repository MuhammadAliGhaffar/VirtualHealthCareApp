package com.example.vhaapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vhaapp.R
import com.example.vhaapp.model.Doctor

class DoctorsAdapter : RecyclerView.Adapter<DoctorsAdapter.ViewHolder>() {
    private var list = mutableListOf<Doctor>()

    /**
     * onClickItem RelativeLayout
     */
    var onItemClick: ((Doctor) -> Unit)? = null

    fun setDoctorList(list: List<Doctor>) {
        this.list = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_doctors, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val doctorModel = list[position]

        if (doctorModel.doctorGender == "Female") {
            Glide.with(holder.imageViewProfile.context).load(R.drawable.female_doctor)
                .into(holder.imageViewProfile)
        } else {
            Glide.with(holder.imageViewProfile.context).load(R.drawable.male_doctor)
                .into(holder.imageViewProfile)
        }

        holder.doctorFirstName.text =
            holder.itemView.resources.getString(R.string.doctor, doctorModel.doctorFirstName)
        holder.doctorSpecialization.text =
            holder.itemView.resources.getString(R.string.post, doctorModel.doctorSpecialization)
        holder.hospitalName.text =
            holder.itemView.resources.getString(R.string.hospital, doctorModel.hospitalName)
        /**
         * Item click implementation
         */
        holder.relativeLL.setOnClickListener {
            onItemClick?.invoke(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewProfile: ImageView = itemView.findViewById(R.id.imageViewProfile)
        val doctorFirstName: TextView = itemView.findViewById(R.id.doctorFirstName)
        val doctorSpecialization: TextView = itemView.findViewById(R.id.doctorSpecialization)
        val hospitalName: TextView = itemView.findViewById(R.id.hospitalName)
        val relativeLL: RelativeLayout = itemView.findViewById(R.id.relativeLL)
    }
}