package com.example.daboyeo_android.ui.writing

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import dagger.hilt.android.AndroidEntryPoint

class LocationActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener {
    private lateinit var binding: ActivityLocationBinding
    lateinit var mapFragment: SupportMapFragment
    private lateinit var mMap: GoogleMap
    private var lat = 0.00
    private var lon = 0.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_location)
        binding.location = this

        mapFragment = supportFragmentManager.findFragmentById(R.id.location_mapView_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        locationZoom()

    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap ?: return
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

        googleMap.setOnMyLocationButtonClickListener(this)

    }

    private fun locationZoom() {
        val manager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            val location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

            if(location != null) {
                lat = location.latitude
                lon = location.longitude
            }

            mMap.isMyLocationEnabled = true

        }

    }

    override fun onMyLocationButtonClick(): Boolean {
        Log.d("Location Activity", "onMyLocationButtonClick")

        return true
    }
}