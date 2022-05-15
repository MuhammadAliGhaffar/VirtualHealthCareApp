package com.example.vhaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vhaapp.utils.KeyValueStore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        KeyValueStore.initPref(activity = this)
    }
}