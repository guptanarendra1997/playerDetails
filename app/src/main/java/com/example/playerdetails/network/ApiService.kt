package com.example.playerdetails.network

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("/sifeeds/cricket/live/json/nzin01312019187360.json")
    fun getPlayer(): Observable<JsonObject>
}