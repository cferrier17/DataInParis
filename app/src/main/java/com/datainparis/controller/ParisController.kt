package com.datainparis.controller

import android.annotation.SuppressLint
import android.location.Location
import com.datainparis.view.activities.MapsActivity
import com.example.model.ParisAPI.WifiResponse
import com.example.projet4a.ProjectConfig
import com.example.projet4a.Util
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ParisController(private val mapsActivity: MapsActivity) {
    private lateinit var BASE_URL : String
    private val markers = arrayListOf<Marker>()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var currentLocation: LatLng = LatLng(48.8534, 2.3488) //initalized at paris coordonates



    fun callParisAPI() {
        emptyMarkersList()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(mapsActivity)

        val projectConfig = Util.getConfig()
        BASE_URL = projectConfig?.baseParisURL!!

        val retrofit = Util.getRetrofit(BASE_URL);
        val parisAPI = Util.getParisAPI()

        var nbHotspots = mapsActivity.getNbHotspots()


        if(nbHotspots=="") {
            nbHotspots="10"
        }

        updateCurrentLocalisation()

        val wifiSpots =  parisAPI?.getWifiSpots(nbHotspots!!.toInt())



        wifiSpots?.enqueue(object: Callback<WifiResponse> {
            override fun onFailure(call: Call<WifiResponse>, t: Throwable) {
                TODO("not implemented")
                print("on failure : ERROR")
            }

            override fun onResponse(call: Call<WifiResponse>, response: Response<WifiResponse>) {

                val body = response.body()
                for ((i, record) in body?.records!!.withIndex()) {
                    val long = record.geometry?.coordinates?.get(0)
                    val lat = record.geometry?.coordinates?.get(1)


                    markers.add(
                        mapsActivity.mMap.addMarker(
                        MarkerOptions()
                            .position(LatLng(lat!!, long!!))
                            .title("${record.fields?.incomingzonelabel}")))

                }
            }

        })
    }





    private fun emptyMarkersList(){
        if(markers.isNotEmpty()){
            markers.forEach{
                it.remove()
            }
            markers.clear()
        }
    }

    private fun updateCurrentLocalisation(){
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                var latitude =  location?.latitude
                var longitude = location?.longitude

                currentLocation = LatLng(longitude!!, latitude!!)
            }
    }



}
