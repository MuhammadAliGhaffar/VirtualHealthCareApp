package com.example.vhaapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.vhaapp.model.Prediction
import com.example.vhaapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PredictionViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val repository: Repository
) : ViewModel() {

    fun getPredictionList(
        id: String,
        callback: (Boolean, String, List<Prediction>) -> Unit = { _: Boolean, _: String, _: List<Prediction> -> }
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getPredictions(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let {

                        callback(true, "List Successfully fetched",it)
                    }
                } else {
                    callback(
                        false,
                        response.code().toString(),
                        emptyList()
                    )
                }
            }
        }

    }
}