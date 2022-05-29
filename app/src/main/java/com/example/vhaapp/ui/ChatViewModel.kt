package com.example.vhaapp.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vhaapp.model.BriefSolution
import com.example.vhaapp.model.Disease
import com.example.vhaapp.model.Symptoms
import com.example.vhaapp.repository.Repository
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val repository: Repository
) : ViewModel() {

    fun predictDisease(
        list: ArrayList<String>,
        patientID: String,
        callback: (Boolean, String) -> Unit = { _: Boolean, _: String -> }
    ) {
        val symptomsObject = Symptoms(list, patientID)

        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.predictDisease(symptomsObject)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string()
                        )
                    )
                    val diseaseObject = gson.fromJson(prettyJson, Disease::class.java)
                    Log.d("AliTag", diseaseObject.disease + "")
                    callback(true, diseaseObject.disease)

                } else {
                    callback(false, "")
                    Log.d("AliTag", response.message() + response.code())
                }
            }
        }


    }

    fun briefSolution(
        disease: String,
        callback: (Boolean, String, BriefSolution) -> Unit = { _: Boolean, _: String, _: BriefSolution -> }
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getBriefSolution(disease)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let {

                        callback(true, "List Successfully fetched", it[0])
                    }
                } else {
                    callback(
                        false,
                        response.code().toString(),
                        BriefSolution(
                            "",
                            "",
                            "",
                            0,
                            "",
                            "",
                            "",
                            "",
                            "",
                            0,
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            0,
                            ""
                        )
                    )
                }
            }
        }

    }

}