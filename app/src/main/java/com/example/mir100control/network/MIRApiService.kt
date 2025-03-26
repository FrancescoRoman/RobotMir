package com.example.mir100control.network

import retrofit2.Call
import retrofit2.http.*

const val BASE_URL = "http://192.168.12.20:80/api/v2.0.0/"

interface MiRApiService {
    @GET("status")
    fun getStatus(
        @Header("Authorization") auth: String
    ): Call<MiRStatus>
    @GET("missions")
    fun getMissions(
        @Header("Authorization") auth: String
    ): Call<List<Mission>>

    @POST("mission_queue")
    fun sendMission(
        @Header("Authorization") auth: String,
        @Body mission: MissionRequest
    ): Call<Void>

    @DELETE("mission_queue")
    fun clearQueue(
        @Header("Authorization") auth: String
    ): Call<Void>
}

