package com.example.daboyeo_android.home.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import com.example.daboyeo_android.base.BaseViewModel
import com.example.daboyeo_android.home.model.HomeRepository

class HomeViewModel @ViewModelInject constructor(val repository: HomeRepository): BaseViewModel() {

}