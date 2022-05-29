package com.example.vhaapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.mapbox.android.core.location.LocationEngineProvider
import com.mapbox.search.MapboxSearchSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App :Application() {

    override fun onCreate() {
        super.onCreate()
        //forcefully set light theme in app
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        MapboxSearchSdk.initialize(
            application = this,
            accessToken = getString(R.string.mapbox_access_token),
            locationEngine = LocationEngineProvider.getBestLocationEngine(this)
        )
    }
}