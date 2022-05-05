package com.example.vhaapp.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
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
class LoginViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val repository: Repository
) : ViewModel() {

    fun loginPatient(
        username: String,
        password: String,
        callback: (Boolean, String) -> Unit = { _: Boolean, _: String -> }
    ) {

        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("username", username)
        jsonObject.put("password", password)

        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.loginPatient(requestBody)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string()
                        )
                    )
                    Log.d("Pretty Printed JSON :", prettyJson)
                    callback(true,"Patient Successfully Logged In")

                } else {
                    callback(false,"Unable to Logged In Error :"+response.code().toString())
                }
            }
        }

    }
}