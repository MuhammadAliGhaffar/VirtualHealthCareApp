package com.example.vhaapp.network

import com.example.vhaapp.model.*
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NetworkDataSource {

    @POST("patient-register")
    suspend fun registerPatient(@Body requestBody: RequestBody): Response<ResponseBody>

    @POST("login")
    suspend fun loginPatient(@Body requestBody: RequestBody): Response<ResponseBody>

    @GET("get-doctors")
    suspend fun getAllDoctors(): Response<List<Doctor>>

    @POST("mark")
    suspend fun predictDisease(@Body symptoms: Symptoms): Response<ResponseBody>

    @GET("disease/view//name/{disease}")
    suspend fun getBriefSolution(@Path("disease") disease: String): Response<List<BriefSolution>>

    @GET("patient/view/{id}")
    suspend fun getSinglePatient(@Path("id") id: Int): Response<Patient>

    @GET("prediction/{id}")
    suspend fun getPredictions(@Path("id") id: String): Response<List<Prediction>>

    @POST("book-appointment")
    suspend fun bookAppointment(@Body requestBody: RequestBody): Response<ResponseBody>

    @GET("appointments")
    suspend fun getAppointments(): Response<List<Appointment>>

}