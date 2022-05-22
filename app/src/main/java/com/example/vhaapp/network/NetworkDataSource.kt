package com.example.vhaapp.network

import com.example.vhaapp.model.BriefSolutionItem
import com.example.vhaapp.model.Doctor
import com.example.vhaapp.model.Patient
import com.example.vhaapp.model.Symptoms
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

    @GET("disease/view//name/{value}")
    suspend fun getBriefSolution(@Path("value") value: String): Response<List<BriefSolutionItem>>

    @GET("patient/view/{value}")
    suspend fun getSinglePatient(@Path("value") value: Int): Response<Patient>

}