package com.example.vhaapp.model

data class Appointment(
    val appointment_date: String,
    val appointment_id: Int,
    val appointment_status: String,
    val appointment_time: String,
    val doctor_fname: String,
    val patient_fname: String
)