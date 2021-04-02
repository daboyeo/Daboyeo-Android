package com.example.daboyeo_android.writing.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import com.example.daboyeo_android.base.BaseViewModel
import com.example.daboyeo_android.writing.repository.WritingRepository

class WritingViewModel @ViewModelInject constructor(val repository: WritingRepository) : BaseViewModel() {

}