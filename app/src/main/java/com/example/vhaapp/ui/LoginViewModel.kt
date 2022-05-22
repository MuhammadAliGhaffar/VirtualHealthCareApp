package com.example.vhaapp.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vhaapp.model.Disease
import com.example.vhaapp.model.LoginSuccessfully
import com.example.vhaapp.repository.Repository
import com.example.vhaapp.utils.ALI_TAG
import com.example.vhaapp.utils.KeyValueStore
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
                    val loginSuccessfullyObject =
                        gson.fromJson(prettyJson, LoginSuccessfully::class.java)
                    Log.d("AliTag", loginSuccessfullyObject.id.toString())
                    singlePatientDetails(id = loginSuccessfullyObject.id)


                    callback(true, "Patient Successfully Logged In")

                } else {
                    callback(false, "Unable to Logged In Error :" + response.code().toString())
                }
            }
        }

    }

    private fun singlePatientDetails(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getSinglePatient(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let {

                        KeyValueStore.setPatientDetails(it)

                        it.patient_fname?.let { it1 -> Log.d("AliTag", it1) }

                    }
                }else {
                    Log.d("AliTag", "Something went wrong while fetching single patent in login -" + response.message() +" - "+response.code())
                }
            }
        }
    }
}