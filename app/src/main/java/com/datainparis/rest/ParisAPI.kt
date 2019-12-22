package com.datainparis.rest

import com.example.model.ParisAPI.WifiResponse
import com.example.projet4a.ProjectConfig
import com.example.projet4a.Util
import retrofit2.Call
import retrofit2.http.*

interface ParisAPI {
    val projectConfig: ProjectConfig?
        get() = Util.getConfig()

    @GET("https://opendata.paris.fr/api/records/1.0/search/?dataset=paris-wi-fi-utilisation-des-hotspots-paris-wi-fi")
    fun getWifiSpots(
        @Query ("rows") nb: Int
    ) : Call<WifiResponse>

//    @GET("https://opendata.paris.fr/api/records/1.0/search/?dataset=paris-wi-fi-utilisation-des-hotspots-paris-wi-fi")
//    fun getWifiSpotsWithLocation(
//        @Query ("rows") nb: Int,
//        @Query("geofilter.distance", encoded = true) lng: Double, lat: Double, distance: Int
//    ) : Call<WifiResponse>
//
//    @GET
//    fun testOkHttp(
//        @Url url: String
//    ) : Call<WifiResponse>


}