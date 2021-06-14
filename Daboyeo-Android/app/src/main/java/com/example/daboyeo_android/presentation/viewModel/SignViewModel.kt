package com.example.daboyeo_android.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daboyeo_android.data.model.sign.GoogleTokenRequest
import kotlinx.coroutines.launch
import kotlin.collections.HashMap
import com.example.daboyeo_android.domain.usecase.sign.GetGoogleTokenUseCase
import com.example.daboyeo_android.domain.usecase.sign.LoginUseCase
import com.example.daboyeo_android.presentation.util.DaboyeoApplication.Companion.pref
import java.lang.Exception
import kotlin.math.log

class SignViewModel(
    private val loginUseCase: LoginUseCase,
    private val getGoogleTokenUseCase: GetGoogleTokenUseCase
) : ViewModel() {
    private var _statusLiveData = MutableLiveData<Int>()
    val statusLiveData: LiveData<Int> get() = _statusLiveData

    private fun login(accessToken: String) {
        var hashMap = HashMap<String, String>()
        hashMap["token"] = accessToken
        hashMap["auth_type"] = "google"

        viewModelScope.launch {
            try {
                val result = loginUseCase.execute(hashMap)
                _statusLiveData.value = result.code
                result.data?.let { saveToken(it) }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun getAccessToken(requestBody: GoogleTokenRequest) {
        viewModelScope.launch {
            try {
                val result = getGoogleTokenUseCase.execute(requestBody)
                login(result.data!!.access_token)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun saveToken(data: String) {
        pref?.saveToken(true, data)
    }

}