package com.datainparis.views

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.datainparis.R

class CameraActivity : AppCompatActivity(), BottomBarFragment.OnBottomFragmentInteractionListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
