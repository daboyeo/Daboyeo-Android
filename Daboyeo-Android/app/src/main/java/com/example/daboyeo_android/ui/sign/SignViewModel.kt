package com.example.daboyeo_android.ui.sign

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daboyeo_android.repository.SignRepository
import kotlinx.coroutines.launch
import com.example.daboyeo_android.http.interceptor.Result

class SignViewModel : ViewModel() {
    private val repository = SignRepository()
    var signLiveData = MutableLiveData<Int>()


    fun signIn(token: String) {
        var hashMap = HashMap<String, String>()
        hashMap.put("token",token)
        hashMap.put("auth_type", "google")

        viewModelScope.launch {
            when(val result = repository.signAuth(hashMap)) {
                is Result.Success -> {
                    if(result.code == 200) {
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