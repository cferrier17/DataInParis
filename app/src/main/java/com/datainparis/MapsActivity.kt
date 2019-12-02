package com.datainparis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.controller.ParisController

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import android.widget.EditText
import android.text.Editable
import android.text.TextWatcher




class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var mMap: GoogleMap
    private lateinit var parisController: ParisController
    private lateinit var yourEditText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val paris = LatLng(48.864716, 2.349014)
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                paris, 12.0f
            )
        )

        val parisController =  ParisController(this)

        yourEditText = findViewById(R.id.editText) as EditText
        yourEditText!!.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
//                parisController.callParisAPI()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
//                parisController.callParisAPI()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                parisController.callParisAPI()
            }
        })



    }

    fun getNbHotspots(): String{
        return yourEditText.text.toString();
    }
}
