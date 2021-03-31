package com.example.daboyeo_android.writing.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.ActivityLocationBinding
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class LocationActivity : AppCompatActivity() , OnMapReadyCallback{
    private lateinit var binding : ActivityLocationBinding
    lateinit var mapFragment: SupportMapFragment
    lateinit var googleMap : GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_location)
        binding.location = this

        mapFragment = supportFragmentManager.findFragmentById(R.id.location_mapView_fragment) as SupportMapFragment
        mapFragment.getMapAsync(OnMapReadyCallback {
            googleMap = it
            //googleMap.isMyLocationEnabled = true

        })

    }

    override fun onMapReady(p0: GoogleMap?) {
        TODO("Not yet implemented")
    }


}