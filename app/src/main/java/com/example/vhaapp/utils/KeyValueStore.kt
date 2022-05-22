package com.example.vhaapp.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.vhaapp.model.Patient


object KeyValueStore {

    private lateinit var sharedPref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    fun initPref(activity: Activity) {
        sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }

    fun clearPref() {
        editor.clear().commit()
    }

    fun setPatientDetails(patient: Patient) {
        editor.putString(PATIENT_AGE, patient.patient_age)
        editor.putString(PATIENT_DOB, patient.patient_age)
        editor.putString(PATIENT_EMAIL, patient.patient_email)
        editor.putString(PATIENT_FIRST_NAME, patient.patient_fname)
        editor.putString(PATIENT_GENDER, patient.patient_gender)
        editor.putString(PATIENT_HEIGHT, patient.patient_height)
        editor.putInt(PATIENT_ID, patient.patient_id)
        editor.putString(PATIENT_LAST_NAME, patient.patient_lname)
        editor.putString(PATIENT_USERNAME, patient.patient_username)
        editor.putString(PATIENT_WEIGHT, patient.patient_weight)
        editor.apply()
    }

    fun getPatientDetails(): Patient {
        return Patient(
            sharedPref.getString(PATIENT_AGE, null),
            sharedPref.getString(PATIENT_DOB, null),
            sharedPref.getString(PATIENT_EMAIL, null),
            sharedPref.getString(PATIENT_FIRST_NAME, null),
            sharedPref.getString(PATIENT_GENDER, null),
            sharedPref.getString(PATIENT_HEIGHT, null),
            sharedPref.getInt(PATIENT_ID, 0),
            sharedPref.getString(PATIENT_LAST_NAME, null),
            sharedPref.getString(PATIENT_USERNAME, null),
            sharedPref.getString(PATIENT_WEIGHT, null)
        )
    }

}