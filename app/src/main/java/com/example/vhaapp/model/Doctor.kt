package com.example.vhaapp.model

import com.google.gson.annotations.SerializedName

data class Doctor(
    val imageViewProfile: Int,
    @SerializedName("current_company")
    val hospitalName: String,
    @SerializedName("designation")
    val doctorDesignation: String,
    @SerializedName("doctor_fname")
    val doctorFirstName: String,
    @SerializedName("doctor_gender")
    val doctorGender: String,
    @SerializedName("doctor_id")
    val doctorID: String,
    @SerializedName("doctor_lname")
    val doctorLastName: String,
    @SerializedName("doctor_username")
    val doctorUserName: String,
    @SerializedName("qualification")
    val doctorQualification: String,
    @SerializedName("specialization")
    val doctorSpecialization: String,
    @SerializedName("year_of_experience")
    val doctorYearOfExperience: String

)