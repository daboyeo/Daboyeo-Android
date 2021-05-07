package com.example.daboyeo_android.ui.writing

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.ActivityLocationBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.Exception

class LocationActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityLocationBinding
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var mMap: GoogleMap
    private var latitude = 0.0
    private var longitude = 0.0
    private val TAG = "Location Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_location)

        mapFragment =
            supportFragmentManager.findFragmentById(R.id.location_mapView_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.locationSetButton.setOnClickListener {
            val intent = Intent()
            setResult(200, intent)
            finish()
        }

        userLocationZoomIn()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap ?: return

        googleMap.setOnMapClickListener {
            val mOptions = MarkerOptions()
            mOptions.title("위치 설정")
            latitude = it.latitude
            longitude = it.longitude
            mOptions.snippet("$latitude, $longitude")
            mMap.addMarker(mOptions)
        }
    }

    private fun userLocationZoomIn() {
        val manager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        try {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.w(TAG, "userLocationZoomIn try : true")

                val location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if (location != null) {
                    latitude = location.latitude
                    longitude = location.longitude
                }
            } else {

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        var latlng = LatLng(latitude, longitude)
        //val update = CameraUpdateFactory.newLatLngZoom(latlng, 15F)
        //mMap.moveCamera(update)
    }
}