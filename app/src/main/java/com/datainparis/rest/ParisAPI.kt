package com.datainparis.rest

import com.example.model.ParisAPI.WifiResponse
import com.example.projet4a.ProjectConfig
import com.example.projet4a.Util
import retrofit2.Call
import retrofit2.http.*

interface ParisAPI {
    @GET("https://opendata.paris.fr/api/records/1.0/search/?dataset=paris-wi-fi-utilisation-des-hotspots-paris-wi-fi")
    fun getWifiSpots(
        @Query ("rows") nb: Int
    ) : Call<WifiResponse>




}