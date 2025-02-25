package com.example.mir100control.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mir100control.network.MiRApiService
import com.example.mir100control.network.Mission
import com.example.mir100control.network.MissionRequest
import com.example.mir100control.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MiRViewModel : ViewModel() {

    private val apiService: MiRApiService = RetrofitClient.apiService
    private val authHeader = "Basic aXRpc2RlbHBvenpvOjlhZDVhYjA0NDVkZTE4ZDI4Nzg0NjMzNzNkNmRiZGIxZWUzZTFmZjg2YzBhYmY4OGJiMzU5YzNkYzVmMzBiNGQ="

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    fun getMissions() {
        apiService.getMissions(authHeader).enqueue(object : Callback<List<Mission>> {
            override fun onResponse(call: Call<List<Mission>>, response: Response<List<Mission>>) {
                if (response.isSuccessful) {
                    _status.value = "Missioni recuperate: ${response.body()?.size}"
                } else {
                    _status.value = "Errore nel recupero missioni"
                }
            }

            override fun onFailure(call: Call<List<Mission>>, t: Throwable) {
                _status.value = "Errore: ${t.message}"
            }
        })
    }

    fun sendMission() {
        val missionRequest = MissionRequest("ffbe035b-0c6d-11ef-97be-00012978ebab")
        apiService.sendMission(authHeader, missionRequest).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                _status.value = if (response.isSuccessful) "Missione inviata!" else "Errore invio missione"
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                _status.value = "Errore: ${t.message}"
            }
        })
    }

    fun clearQueue() {
        apiService.clearQueue(authHeader).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                _status.value = if (response.isSuccessful) "Coda cancellata!" else "Errore cancellazione coda"
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                _status.value = "Errore: ${t.message}"
            }
        })
    }
}
