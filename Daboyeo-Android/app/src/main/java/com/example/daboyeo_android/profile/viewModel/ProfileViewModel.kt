package com.example.daboyeo_android.profile.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import com.example.daboyeo_android.base.BaseViewModel
import com.example.daboyeo_android.profile.model.ProfileRepository

class ProfileViewModel @ViewModelInject constructor(val repository: ProfileRepository): BaseViewModel() {
}