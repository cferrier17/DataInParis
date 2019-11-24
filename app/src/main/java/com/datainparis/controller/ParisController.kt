package com.example.controller

import com.datainparis.MapsActivity
import com.example.model.ParisAPI.WifiResponse
import com.example.projet4a.ProjectConfig
import com.example.projet4a.Util
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.io.File

class ParisController(private val mainActivity: MapsActivity) {
    internal val BASE_URL = "https://opendata.paris.fr/api/records/1.0/"
    private var projectConfig: ProjectConfig? = null

    private val PREFS = "PREFS"


    fun startWifi() {
        callParisAPI()



    }



    fun callParisAPI() {
        val retrofit = Util.getRetrofit(BASE_URL);
        val parisAPI = Util.getParisAPI()


        val wifiSpots = parisAPI?.getWifiSpots()

        wifiSpots?.enqueue(object: Callback<WifiResponse> {
            override fun onFailure(call: Call<WifiResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<WifiResponse>, response: Response<WifiResponse>) {
                val body = response.body()
                println(body)
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
