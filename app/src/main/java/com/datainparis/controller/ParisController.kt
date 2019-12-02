package com.example.controller

import com.datainparis.MapsActivity
import com.example.model.ParisAPI.WifiResponse
import com.example.projet4a.ProjectConfig
import com.example.projet4a.Util
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


import java.io.File

class ParisController(private val mapsActivity: MapsActivity) {
    internal val BASE_URL = "https://opendata.paris.fr/api/records/1.0/"
    private var projectConfig: ProjectConfig? = null
    private val PREFS = "PREFS"
    private val markers = arrayListOf<Marker>()

    fun startWifi() {
        callParisAPI()



    }



    fun callParisAPI() {
        val retrofit = Util.getRetrofit(BASE_URL);
        val parisAPI = Util.getParisAPI()

        var nbHotspots = mapsActivity.getNbHotspots()

        if(nbHotspots=="") {
            nbHotspots="10"
        }

        val wifiSpots = parisAPI?.getWifiSpots(nbHotspots!!.toInt())

        wifiSpots?.enqueue(object: Callback<WifiResponse> {
            override fun onFailure(call: Call<WifiResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<WifiResponse>, response: Response<WifiResponse>) {
                markers.removeAll(markers)
                val body = response.body()
                println(body)

                for (record in body?.records!!) {
                    val lat = record.geometry?.coordinates?.get(0)
                    val long = record.geometry?.coordinates?.get(1)
                    val marker = LatLng(long!!, lat!!)

//                    markers.add(marker);

                    //todo : changer title
                    mapsActivity.mMap.addMarker(MarkerOptions().position(marker).title("Marker in Sydney"))
                }
            }

        })
    }



    fun getConfig(): ProjectConfig? {
        println("Working Directory = " +
                System.getProperty("user.dir"));
        val mapper = ObjectMapper(YAMLFactory())
        try {
            return mapper.readValue(File("app/src/main/java/com/example/config/application-config.yml"), ProjectConfig::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null;
    }

}
