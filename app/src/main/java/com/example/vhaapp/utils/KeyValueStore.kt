package com.example.vhaapp.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences


object KeyValueStore {

    private lateinit var sharedPref : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor

    fun initPref(activity: Activity) {
        sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }

    fun clearPref() {
        editor.clear()
    }

}