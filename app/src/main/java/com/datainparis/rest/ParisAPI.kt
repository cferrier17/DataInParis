package com.example.rest

import com.example.model.ParisAPI.WifiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ParisAPI {

    @GET("https://opendata.paris.fr/api/records/1.0/search/?dataset=paris-wi-fi-utilisation-des-hotspots-paris-wi-fi")
    fun getWifiSpots(
        @Query ("rows") nb: Int
    ) : Call<WifiResponse>




}