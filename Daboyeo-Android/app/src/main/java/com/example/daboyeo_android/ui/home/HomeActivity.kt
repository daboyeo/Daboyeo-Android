package com.example.daboyeo_android.ui.home

import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.ActivityHomeBinding
import com.example.daboyeo_android.ui.profile.ProfileFragment
import com.example.daboyeo_android.ui.search.SearchFragment
import com.example.daboyeo_android.ui.writing.WritingFragment

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

        bottomNavigationItemClick()
        changeView(WritingFragment(),"Writing")
        checkPermissions()
    }

    private fun checkPermissions() {
        var rejectedPermissionList = ArrayList<String>()

        for (permission in requiredPermissions) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                rejectedPermissionList.add(permission)
            }
        }

        if (rejectedPermissionList.isNotEmpty()) {
            val array = arrayOfNulls<String>(rejectedPermissionList.size)
            ActivityCompat.requestPermissions(
                this, rejectedPermissionList.toArray(array), REQUEST_PERMISSIONS
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_PERMISSIONS -> {
                for ((i, permission) in permissions.withIndex()) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        Log.i("HomeActivity", "the user has denied to $permission")
                    }
                }
            }
        }
    }

    private fun bottomNavigationItemClick() {
        binding.homeBottomNavigation.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.menu_home_main -> changeView(HomeFragment(), "Home")
                R.id.menu_home_search -> changeView(SearchFragment(), "Search")
                R.id.menu_home_writing -> changeView(WritingFragment(), "Writing")
                R.id.menu_home_profile -> changeView(ProfileFragment(), "MyProfile")
            }
        }
    }

    fun changeView(fragment: Fragment, toolbar: String) {
        replaceFragment(fragment)
        setToolbar(toolbar)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.home_fragment, fragment)
        fragmentTransaction.commit()
    }

    private fun setToolbar(fragment: String) {
        when (fragment) {
            "Home" -> binding.toolbarTitleTextView.text = getString(R.string.app_name)
            "Writing" -> {
                binding.toolbarTitleTextView.text = getString(R.string.writing)
                binding.toolbarSaveButton.visibility = View.VISIBLE
                binding.toolbarBackButton.visibility = View.VISIBLE
            }
            "MyProfile" -> {
                binding.toolbarTitleTextView.text = getString(R.string.my_profile)
                binding.toolbarBackButton.visibility = View.VISIBLE
                binding.toolbarSettingButton.visibility = View.VISIBLE
            }
            "Profile" -> {
                binding.toolbarTitleTextView.text = getString(R.string.profile)
                binding.toolbarBackButton.visibility = View.VISIBLE
            }
            "Search" -> binding.homeToolbar.visibility = View.GONE
            "Detail" -> binding.toolbarBackButton.visibility = View.VISIBLE

        }
    }
}