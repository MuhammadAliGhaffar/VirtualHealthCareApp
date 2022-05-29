package com.example.vhaapp.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vhaapp.model.LoginSuccessfully
import com.example.vhaapp.repository.Repository
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class BookAppointmentViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val repository: Repository
) : ViewModel() {

    fun bookAppointment(
        doctorID: String,
        patientID: String,
        appointmentTime: String,
        appointmentDate: String,
        comment: String,
        callback: (Boolean, String) -> Unit = { _: Boolean, _: String -> }
    ) {

        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("doctor_id", doctorID)
        jsonObject.put("patient_id", patientID)
        jsonObject.put("appointment_time", appointmentTime)
        jsonObject.put("appointment_date", appointmentDate)
        jsonObject.put("appointment_comment", comment)

        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.bookAppointment(requestBody)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    callback(true, "Appointment Booked")
                } else {
                    callback(false, "Unable to Booked Appointment :" + response.code().toString() + response.message().toString())
                }
            }
        }

    }

}