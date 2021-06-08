package com.example.daboyeo_android.ui.signp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daboyeo_android.entity.sign.GoogleTokenRequest
import com.example.daboyeo_android.repository.SignRepository
import kotlinx.coroutines.launch
import kotlin.collections.HashMap
import com.example.daboyeo_android.http.interceptor.Result
import com.example.daboyeo_android.util.DaboyeoApplication.Companion.pref

class SignViewModel : ViewModel() {
    private val repository = SignRepository()
    private var _statusLiveData = MutableLiveData<Int>()
    val statusLiveData: LiveData<Int>  get() = _statusLiveData

    private fun login(accessToken: String) {
        var hashMap = HashMap<String, String>()

        hashMap["token"] = accessToken
        hashMap["auth_type"] = "google"

        viewModelScope.launch {
            when (val result = repository.signAuth(hashMap)) {
                is Result.Success -> {
                    _statusLiveData.value = result.code
                    saveToken(result.data)
                }
                is Result.Error -> {
                    Log.e("SignViewModel", result.exception)
                }
            }
        }
    }

    fun getAccessToken(requestBody: GoogleTokenRequest) {
        viewModelScope.launch {
            when(val result = repository.googleAccessToken(requestBody)) {
                is Result.Success -> {
                    login(result.data.access_token)
                }
                is Result.Error -> {
                    Log.e("SignViewModel", result.exception)
                }
            }
        }

    }

    private fun saveToken(data: String) {
        pref?.saveToken(true, data)
    }

}