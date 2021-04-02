package com.example.daboyeo_android.sign.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import com.example.daboyeo_android.base.BaseViewModel
import com.example.daboyeo_android.sign.model.LoginRepository

class LoginViewModel @ViewModelInject constructor(val repository: LoginRepository) : BaseViewModel(){
}