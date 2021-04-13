package com.example.daboyeo_android.ui.sign

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.daboyeo_android.repository.SignRepository

class SignViewModel @ViewModelInject constructor(
        private val repository: SignRepository
): ViewModel() {

}