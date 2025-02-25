package com.example.mir100control.network

data class Mission(
    val guid: String,
    val name: String
)

data class MissionRequest(
    val mission_id: String
)
