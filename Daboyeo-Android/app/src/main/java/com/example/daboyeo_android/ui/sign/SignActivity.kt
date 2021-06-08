package com.example.daboyeo_android.ui.sign

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.daboyeo_android.BuildConfig
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.ActivitySignBinding
import com.example.daboyeo_android.entity.sign.GoogleTokenRequest
import com.example.daboyeo_android.ui.home.HomeActivity
import com.example.daboyeo_android.ui.signp.SignViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task

private const val TAG = "SignActivity_TAG"
private const val RC_SIGN_IN = 200

class SignActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignBinding
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val viewModel = SignViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign)
        
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestScopes(Scope(Scopes.DRIVE_APPFOLDER))
                .requestServerAuthCode(getString(R.string.default_web_client_id))
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.signLoginButton.setOnClickListener {
            signIn()
        }

        viewModel.statusLiveData.observe(this, {
            when (it) {
                200 -> {
                    intent()
                }
                else -> {
                    Toast.makeText(this, getString(R.string.unknown_request), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            val authCode = account?.serverAuthCode
            val request = GoogleTokenRequest(
                    "authorization_code",
                    getString(R.string.default_web_client_id),
                    BuildConfig.CLIENT_SECRET,
                    "http://127.0.0.1",
                    authCode.toString(),
                    account?.idToken.toString())

            viewModel.getAccessToken(request)

        } catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
        }
    }

    private fun intent() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}