package com.example.vhaapp.repository

import com.example.vhaapp.network.NetworkDataSource
import okhttp3.RequestBody
import retrofit2.http.Body

class Repository(private val networkDataSource: NetworkDataSource) {

    suspend fun registerPatient(@Body requestBody: RequestBody) =
        networkDataSource.registerPatient(requestBody)

    suspend fun loginPatient(@Body requestBody: RequestBody) =
        networkDataSource.loginPatient(requestBody)

    suspend fun getAllDoctors() =
        networkDataSource.getAllDoctors()
}