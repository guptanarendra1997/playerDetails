package com.example.playerdetails.viewmodel

import androidx.lifecycle.LiveData
import com.example.playerdetails.network.ApiService
import com.google.gson.JsonObject

class PlayerDetailsViewModel : BaseViewModel() {

    fun getPlayer(apiInterface: ApiService): LiveData<JsonObject> {
        return liveData(apiInterface.getPlayer())
    }
}