package com.example.vhaapp.network

import com.example.vhaapp.model.Doctor
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NetworkDataSource {

    @POST("patient-register")
    suspend fun registerPatient(@Body requestBody: RequestBody): Response<ResponseBody>

    @POST("login")
    suspend fun loginPatient(@Body requestBody: RequestBody): Response<ResponseBody>

    @GET("doctors")
    suspend fun getAllDoctors(): Response<List<Doctor>>

}