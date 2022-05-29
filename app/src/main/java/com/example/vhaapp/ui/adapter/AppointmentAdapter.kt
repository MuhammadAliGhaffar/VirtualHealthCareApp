package com.example.vhaapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vhaapp.R
import com.example.vhaapp.model.Appointment

class AppointmentAdapter : RecyclerView.Adapter<AppointmentAdapter.ViewHolder>() {
    private var list = mutableListOf<Appointment>()


    fun setAppointmentList(list: List<Appointment>) {
        this.list = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_appointments, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appointmentModel = list[position]

        holder.doctorName.text =
            holder.itemView.resources.getString(
                R.string.doctor_name,
                appointmentModel.doctor_fname
            )
        holder.patientName.text =
            holder.itemView.resources.getString(
                R.string.patient_name_appointment,
                appointmentModel.patient_fname
            )
        holder.appointmentDate.text =
            holder.itemView.resources.getString(
                R.string.appointment_date,
                appointmentModel.appointment_date
            )
        holder.appointmentTime.text = holder.itemView.resources.getString(
            R.string.appointment_time,
            appointmentModel.appointment_time
        )

        if ((appointmentModel.appointment_status == "Pending")) {
            holder.statusContainer.setBackgroundColor(holder.itemView.resources.getColor(R.color.red))
        } else {
            holder.statusContainer.setBackgroundColor(holder.itemView.resources.getColor(R.color.green))
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val doctorName: TextView = itemView.findViewById(R.id.doctorName)
        val patientName: TextView = itemView.findViewById(R.id.patientName)
        val appointmentDate: TextView = itemView.findViewById(R.id.appointmentDate)
        val appointmentTime: TextView = itemView.findViewById(R.id.appointmentTime)
        val statusContainer: RelativeLayout = itemView.findViewById(R.id.statusContainer)
    }

}