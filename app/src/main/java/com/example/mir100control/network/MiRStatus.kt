package com.example.mir100control.network

import com.google.gson.annotations.SerializedName

data class MiRStatus(
    @SerializedName("state_text") val state: String,
    @SerializedName("battery_percentage") val batteryPercentage: Float
)

