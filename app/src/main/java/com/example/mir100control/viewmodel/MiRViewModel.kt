package com.example.mir100control.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mir100control.network.MiRApiService
import com.example.mir100control.network.MiRStatus

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

    private val _battery = MutableLiveData<Float>()
    val battery: LiveData<Float> = _battery

    private val _missions = MutableLiveData<List<Mission>>()
    val missions: LiveData<List<Mission>> = _missions

    private val _selectedMission = MutableLiveData<String?>()
    val selectedMission: LiveData<String?> = _selectedMission

    init {
        getMirStatus()
        getMissions()
    }

    fun getMirStatus() {
        apiService.getStatus(authHeader).enqueue(object : Callback<MiRStatus> {
            override fun onResponse(call: Call<MiRStatus>, response: Response<MiRStatus>) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    _status.value = " ${responseData?.state ?: "Sconosciuto"}"
                    _battery.value = responseData?.batteryPercentage ?: 0f
                } else {
                    _status.value = "Errore aggiornamento stato"
                }
            }

            override fun onFailure(call: Call<MiRStatus>, t: Throwable) {
                _status.value = "Errore: ${t.message}"
            }
        })
    }

    fun getMissions() {
        apiService.getMissions(authHeader).enqueue(object : Callback<List<Mission>> {
            override fun onResponse(call: Call<List<Mission>>, response: Response<List<Mission>>) {
                if (response.isSuccessful) {
                    _missions.value = response.body() ?: emptyList()
                } else {
                    _status.value = "Errore nel recupero missioni"
                }
            }

            override fun onFailure(call: Call<List<Mission>>, t: Throwable) {
                _status.value = "Errore: ${t.message}"
            }
        })
    }

    fun selectMission(missionId: String) {
        _selectedMission.value = missionId
    }

    fun sendMission() {
        val missionId = _selectedMission.value
        if (missionId == null) {
            _status.value = "Seleziona una missione prima di inviare"
            return
        }

        val missionRequest = MissionRequest(missionId)
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

