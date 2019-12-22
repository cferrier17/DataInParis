package com.datainparis.views

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.datainparis.controller.ParisController

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import android.widget.EditText
import android.text.Editable
import android.text.TextWatcher
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.datainparis.R


class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
    OptionsBarFragment.OnOptionsBarFragmentInteractionListener,
    BottomBarFragment.OnBottomFragmentInteractionListener{


    lateinit var mMap: GoogleMap
    private lateinit var parisController: ParisController
    private lateinit var myEditText: EditText


    private val permissionsRequest = 100
    var  MY_PERMISSIONS_REQUEST_COARSE_LOCATION : Int = 0
    var  MY_PERMISSIONS_REQUEST_FINE_LOCATION : Int = 0
    private lateinit var optionsBarFragment: OptionsBarFragment
    private lateinit var bottomBarFragment: BottomBarFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        optionsBarFragment = OptionsBarFragment()
        bottomBarFragment = BottomBarFragment()

        requestLocationPermissions()

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




        myEditText = findViewById(R.id.editText2) as EditText
        myEditText!!.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                parisController.callParisAPI()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
        })





        parisController.callParisAPI()
    }

    fun getNbHotspots(): String{

        return myEditText.text.toString();
    }

    private fun requestLocationPermissions() {
        // Here, mapsActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
            &&
            ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)
                &&
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    this.MY_PERMISSIONS_REQUEST_COARSE_LOCATION)

                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    this.MY_PERMISSIONS_REQUEST_FINE_LOCATION)

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is OptionsBarFragment) {
            fragment.setOptionBarSelectedListener(this)
        }
    }

    override fun onOptionsBarFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun launchActivity(view : View) {
        val intent : Intent
        val activityName = view.getTag()
        when(activityName) {
            "camera" -> intent = Intent(this, CameraActivity::class.java)
            "map" -> intent = Intent(this, MapsActivity::class.java)
            else -> intent = Intent(this, MapsActivity::class.java)
        }

        this?.startActivity(intent)
    }





}
