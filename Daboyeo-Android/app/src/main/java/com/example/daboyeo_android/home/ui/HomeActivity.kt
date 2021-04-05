package com.example.daboyeo_android.home.ui

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.ActivityHomeBinding
import com.example.daboyeo_android.profile.ui.ProfileFragment
import com.example.daboyeo_android.writing.ui.WritingFragment

private const val REQUEST_PERMISSIONS = 100

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private val requiredPermissions = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.homeAct = this

        bottomNavigationItemClick()
        replaceFragment(HomeFragment())
        checkPermissions()
    }

    private fun checkPermissions() {
        var rejectedPermissionList = ArrayList<String>()

        for (permission in requiredPermissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                rejectedPermissionList.add(permission)
            }
        }

        if (rejectedPermissionList.isNotEmpty()) {
            val array = arrayOfNulls<String>(rejectedPermissionList.size)
            ActivityCompat.requestPermissions(this, rejectedPermissionList.toArray(array), REQUEST_PERMISSIONS)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when(requestCode) {
            REQUEST_PERMISSIONS -> {
                for((i,permission) in permissions.withIndex()) {
                    if(grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        Log.i("HomeActivity","the user has denied to $permission")
                    }
                }
            }
        }
    }

    private fun bottomNavigationItemClick() {
        binding.homeBottomNavigation.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.menu_home_main -> {
                    replaceFragment(HomeFragment())
                }

                R.id.menu_home_search -> {
                    replaceFragment(SearchFragment())
                }

                R.id.menu_home_writing -> {
                    replaceFragment(WritingFragment())
                }

                R.id.menu_home_profile -> {
                    replaceFragment(ProfileFragment())
                }
            }
        }
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.home_fragment, fragment)
        fragmentTransaction.commit()
    }

}