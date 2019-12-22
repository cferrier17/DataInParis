package com.datainparis.controller

import android.annotation.SuppressLint
import android.location.Location
import com.datainparis.views.MapsActivity
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
    internal val BASE_URL = "https://opendata.paris.fr/api/records/1.0/search/"
    internal val WIFI_DATASET = "?dataset=paris-wi-fi-utilisation-des-hotspots-paris-wi-fi"
    private var projectConfig: ProjectConfig? = null
    private val PREFS = "PREFS"
    private val markers = arrayListOf<Marker>()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var currentLocation: LatLng = LatLng(48.8534, 2.3488) //initalize at paris coordonates



    fun callParisAPI() {
        emptyMarkersList()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(mapsActivity)

//        updateCurrentLocalisation()

        val retrofit = Util.getRetrofit(BASE_URL);
        val parisAPI = Util.getParisAPI()

        var nbHotspots = mapsActivity.getNbHotspots()


        if(nbHotspots=="") {
            nbHotspots="10"
        }



        val wifiSpots =  parisAPI?.getWifiSpots(nbHotspots!!.toInt())

//        val wifiSpots = parisAPI?.getWifiSpots(nbHotspots.toInt())

//        var url =
//            HttpUrl.parse(BASE_URL+WIFI_DATASET
//                    +"&rows="+nbHotspots
//                    +"&geofilter.distance="+currentLocation.latitude+"%2C"+currentLocation.longitude+"%2C"+20000)

//        val url = HttpUrl.parse("https://opendata.paris.fr/api/records/1.0/search/?dataset=paris-wi-fi-utilisation-des-hotspots-paris-wi-fi&rows=10&geofilter.polygon=(48.851190%2C+2.312021)%2C+(48.851190%2C+2.308418)%2C+(48.839894%2C+2.393294)")
//        val wifiSpots = parisAPI?.testOkHttp(url.toString())


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
                    val marker = LatLng(lat!!, long!!)

//                    markers.add(marker);
//                    val title = MarkerOptions().position(marker).title("Marker in Sydney")
                    //todo : changer title
                        mapsActivity.mMap.addMarker(
                            MarkerOptions()
                                .position(LatLng(lat, long))
                                .title("Marker $i ${record.fields?.incomingzonelabel}"))

                    mapsActivity.mMap.addMarker(
                        MarkerOptions()
                            .position(LatLng(48.8534, 2.3480))
                            .title("Hello world")
                    )

                }
            }

        })
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
