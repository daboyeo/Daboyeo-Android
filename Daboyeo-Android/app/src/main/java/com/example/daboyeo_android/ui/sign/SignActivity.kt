package com.example.daboyeo_android.ui.sign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.daboyeo_android.R
import dagger.hilt.android.AndroidEntryPoint

class SignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.sign_fragment, SignFragment())
        fragmentTransaction.commit()
    }
}