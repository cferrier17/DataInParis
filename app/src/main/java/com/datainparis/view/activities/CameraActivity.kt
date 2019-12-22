package com.datainparis.view.activities

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Camera
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
import android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.datainparis.R
import com.datainparis.projet4a.CameraPreview
import com.datainparis.view.fragments.BottomBarFragment
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CameraActivity : AppCompatActivity(), BottomBarFragment.OnBottomFragmentInteractionListener  {
    private var myCamera: Camera? = null
    private var myPreview: CameraPreview? = null
    var  MY_PERMISSIONS_REQUEST_CAMERA: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        if(checkCameraHardware(this)) {
            requestCameraPermissions()

            myCamera = getCameraInstance()

            myPreview = myCamera?.let{
                CameraPreview(this, it)
            }

            myPreview?.also {
                val preview: FrameLayout = findViewById(R.id.camera_preview)
                preview.addView(it)
            }

            val captureButton: Button = findViewById(R.id.button_capture)
            captureButton.setOnClickListener {
                myCamera?.takePicture(null, null, mPicture)
            }
        }



    }

    override fun onPause() {
        super.onPause()
        myCamera?.release()
        myCamera = null
    }


    private fun checkCameraHardware(context: Context): Boolean {
        return context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)
    }

    fun getCameraInstance(): Camera? {
        return try {
            Camera.open()
        } catch (e: Exception) {
            null
        }
    }

    override fun onFragmentInteraction(uri: Uri) {

    }


    private fun requestCameraPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {

            } else {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.CAMERA),
                    this.MY_PERMISSIONS_REQUEST_CAMERA)
            }
        } else {

        }
    }

    private val mPicture = Camera.PictureCallback { data, _ ->
        val pictureFile: File = getOutputMediaFile(MEDIA_TYPE_IMAGE) ?: run {
            return@PictureCallback
        }

        try {
            val fos = FileOutputStream(pictureFile)
            fos.write(data)
            fos.close()
        } catch (e: FileNotFoundException) {
        } catch (e: IOException) {
        }
    }

    private fun getOutputMediaFile(type: Int): File? {

        val mediaStorageDir = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
            "MyCameraApp"
        )

        mediaStorageDir.apply {
            if (!exists()) {
                if (!mkdirs()) {
                    Log.d("MyCameraApp", "failed to create directory")
                    return null
                }
            }
        }

        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        return when (type) {
            MEDIA_TYPE_IMAGE -> {
                File("${mediaStorageDir.path}${File.separator}IMG_$timeStamp.jpg")
            }
            else -> null
        }
    }
}
