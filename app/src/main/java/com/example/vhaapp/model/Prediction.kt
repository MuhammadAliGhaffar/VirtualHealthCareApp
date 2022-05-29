package com.example.vhaapp.model

data class Prediction(
    val disease_name: String,
    val patient_id: Int,
    val prediction_date: String,
    val prediction_id: Int
)