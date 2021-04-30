package com.example.daboyeo_android.ui.sign

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.ActivitySignBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class SignActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignBinding
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private val RC_SIGN_IN = 200
    private var token: String? = null
    private val TAG = "SignActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign)

        binding.signLoginButton.setSize(SignInButton.SIZE_STANDARD)

        var gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
                .requestEmail()
                .build()

        /*val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()*/

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.signLoginButton.setOnClickListener {
            signIn()
        }

    }

    override fun onStart() {
        super.onStart()

        var account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)

        //updateUI(account)
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient?.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        } else {
            Log.w(TAG, "onActivityResult: requestCode fail")
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)

            if(account != null) {
                Log.w(TAG, account.id.toString())
                Log.w(TAG, account.email.toString())
                Log.w(TAG, account.idToken.toString())

            } else {
                Log.w(TAG, "handleSignInResult: get account fail")
            }
            //updateUI(account)
        } catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            //updateUI(null)
        }
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        if(account != null) {
            token = account.idToken
        } else {
            Log.w(TAG, "updateUI: account is null")
        }
    }
}