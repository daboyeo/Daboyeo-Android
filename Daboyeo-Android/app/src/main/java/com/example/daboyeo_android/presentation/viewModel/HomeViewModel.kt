package com.example.daboyeo_android.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daboyeo_android.data.model.home.PostListData
import com.example.daboyeo_android.domain.repository.HomeRepository
import com.example.daboyeo_android.domain.usecase.home.GetTimeLineUseCase
import com.example.daboyeo_android.domain.usecase.post.GetDetailPostUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(
    private val getTimeLineUseCase: GetTimeLineUseCase
) : ViewModel() {
    private val _reportsData = MutableLiveData<PostListData>()
    val reportsData: LiveData<PostListData> get() = _reportsData

    init {
        getTimeLine()
    }

    private fun getTimeLine() {
        viewModelScope.launch {
            val result = getTimeLineUseCase.getTimeLine()
            try {
                _reportsData.value = result.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}