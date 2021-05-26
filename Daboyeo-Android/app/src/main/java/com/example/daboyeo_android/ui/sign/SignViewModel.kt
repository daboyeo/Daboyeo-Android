package com.example.daboyeo_android.ui.sign

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daboyeo_android.repository.SignRepository
import kotlinx.coroutines.launch
import com.example.daboyeo_android.http.interceptor.Result
import com.google.android.gms.auth.GoogleAuthUtil
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.GooglePlayServicesUtil
import kotlinx.coroutines.Dispatchers

class SignViewModel : ViewModel() {
    private val repository = SignRepository()
    var signLiveData = MutableLiveData<Int>()
    private val scope = "audience:server:client_id:579492909337-40q0b10l9bidbcdofmm1uggi95clbbn0.apps.googleusercontent.com"

    private fun getAccessToken(account: GoogleSignInAccount, applicationContext: Context){
        viewModelScope.launch(Dispatchers.IO) {
            getAccessToken = GoogleAuthUtil.getToken(applicationContext, account.account, scope)
            Log.e("SignViewModel", "token in scope : $getAccessToken")
        }
    }

    fun signIn(account: GoogleSignInAccount, applicationContext: Context) {
        //getAccessToken(account, applicationContext)
        var hashMap = HashMap<String, String>()

        if(getAccessToken != null) {
            hashMap["token"] = getAccessToken
            hashMap["auth_type"] = "google"
        } else {
            Log.e("SignViewModel", "getAccessToken is null")
        }

        viewModelScope.launch {
            when (val result = repository.signAuth(hashMap)) {
                is Result.Success -> {
                    if (result.code == 200) {
                        signLiveData.value = 1
                        Log.d("SignViewModel", "token : " + result.data)
                    }
                }
                is Result.Error -> {
                    signLiveData.value = 0
                    Log.e("SignViewModel", result.exception)
                }
            }
        }
    }
}