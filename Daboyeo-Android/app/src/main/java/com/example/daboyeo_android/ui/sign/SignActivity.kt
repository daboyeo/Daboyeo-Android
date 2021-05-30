package com.example.daboyeo_android.ui.sign

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.daboyeo_android.R
import com.example.daboyeo_android.databinding.ActivitySignBinding
import com.example.daboyeo_android.ui.home.HomeActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject

class SignActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignBinding
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 200
    private val TAG = "SignActivity_TAG"
    private val viewModel = SignViewModel()
    private var clientSecret = "Pq6vpyaO1dSMZxnGmnJVtApz"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign)

        binding.signLoginButton.setSize(SignInButton.SIZE_STANDARD)

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

        binding.signNoSignButton.setOnClickListener {
            intent()
        }

        viewModel.signLiveData.observe(this, {
            when (it) {
                1 -> {
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

            val client = OkHttpClient()
            val requestBody: RequestBody = FormBody.Builder()
                    .add("grant_type", "authorization_code")
                    .add("client_id", getString(R.string.default_web_client_id))
                    .add("client_secret", clientSecret)
                    .add("redirect_uri", "http://127.0.0.1")
                    .add("code", authCode.toString())
                    .add("id_token", account?.idToken.toString())
                    .build()

            val request: Request = Request.Builder()
                    .url("https://www.googleapis.com/oauth2/v4/token")
                    .post(requestBody)
                    .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: java.io.IOException) {
                    Log.e(TAG, "onFailure : $e")
                }

                override fun onResponse(call: Call, response: Response) {
                    try {
                        Log.e(TAG, "onResponse : ${response.code}")

                        val jsonObject = JSONObject(response.body?.string())
                        val message: String = jsonObject.toString(5)
                        var accessToken = jsonObject.getString("access_token")

                        Log.e(TAG, message)
                        runOnUiThread {
                            viewModel.login(accessToken)
                        }

                    } catch (e: JSONException) {
                        Log.e(TAG, e.toString())
                        e.printStackTrace()
                    }
                }
            })
        } catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
        }
    }

    private fun intent() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}