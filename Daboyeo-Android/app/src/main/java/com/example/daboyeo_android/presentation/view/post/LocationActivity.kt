package com.example.daboyeo_android.presentation.view.post

import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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

class LocationActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityLocationBinding
    private lateinit var mapFragment: SupportMapFragment
    private var mMap: GoogleMap? = null
    private var latitude = 0.0
    private var longitude = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_location)

        mapFragment = supportFragmentManager.findFragmentById(R.id.location_mapView_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.locationSetButton.setOnClickListener {
            val intent = Intent()
            intent.putExtra("위도", latitude)
            intent.putExtra("경도", longitude)
            setResult(200, intent)
            finish()
        }

        goToLocationZoom();
    }

    override fun onMapReady(googleMap: GoogleMap) {
        Log.e("LocationActivity", "onMapReady")
        mMap = googleMap

        googleMap.setOnMapClickListener { latLng ->
            val mOptions = MarkerOptions()
            mOptions.title("위치 설정")
            latitude = latLng.latitude
            longitude = latLng.longitude
            mOptions.snippet("$latitude, $longitude")
            mOptions.position(LatLng(latitude, longitude))
            mMap!!.addMarker(mOptions)
            Log.e("locationActivity", "위도: $latitude")
            Log.e("locationActivity", "경도: $longitude")
        }
    }

    private fun goToLocationZoom() {
        Log.e("LocationActivity", "goToLocationZoom")
        val manager = getSystemService(LOCATION_SERVICE) as LocationManager
        try {
            val location: Location? = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (location != null) {
                latitude = location.latitude
                longitude = location.longitude
            }
            Log.e("LocationActivity", "goToLocationZoom try")

        } catch (e: SecurityException) {
            e.printStackTrace()
            Log.e("LocationActivity", "goToLocationZoom catch")
        }
        val latLng = LatLng(latitude, longitude)
        val update = CameraUpdateFactory.newLatLngZoom(latLng, 15f)
        mMap?.moveCamera(update)
    }

}

