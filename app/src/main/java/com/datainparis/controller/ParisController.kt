package com.example.controller

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.datainparis.MapsActivity
import com.example.model.ParisAPI.WifiResponse
import com.example.projet4a.ProjectConfig
import com.example.projet4a.Util
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
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
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var currentLocation: LatLng = LatLng(48.8534, 2.3488) //initalize at paris coordonates



    fun callParisAPI() {
        emptyMarkersList()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(mapsActivity)

        updateCurrentLocalisation()

        val retrofit = Util.getRetrofit(BASE_URL);
        val parisAPI = Util.getParisAPI()

        var nbHotspots = mapsActivity.getNbHotspots()

        if(nbHotspots=="") {
            nbHotspots="10"
        }



        val wifiSpots =  parisAPI?.getWifiSpotsWithLocation(nbHotspots!!.toInt(),
            10.0,
            10.0,
            1000)



        wifiSpots?.enqueue(object: Callback<WifiResponse> {
            override fun onFailure(call: Call<WifiResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<WifiResponse>, response: Response<WifiResponse>) {

                val body = response.body()

                for (record in body?.records!!) {
                    val lat = record.geometry?.coordinates?.get(0)
                     val long = record.geometry?.coordinates?.get(1)
                    val marker = LatLng(long!!, lat!!)

//                    markers.add(marker);
                    val title = MarkerOptions().position(marker).title("Marker in Sydney")
                    //todo : changer title
                    val addedMarker =
                        mapsActivity.mMap.addMarker(MarkerOptions().position(marker).title("Marker in Sydney"))
                    markers.add(addedMarker)

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

    fun emptyMarkersList(){
        if(markers.isNotEmpty()){
            markers.forEach{
                it.remove()
            }
            markers.clear()
        }
    }

    @SuppressLint("MissingPermission")
    private fun updateCurrentLocalisation(){
        lateinit var latLng: LatLng
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                var latitude =  location?.latitude
                var longitude = location?.longitude

                currentLocation = LatLng(longitude!!, latitude!!)
            }


    }



}
