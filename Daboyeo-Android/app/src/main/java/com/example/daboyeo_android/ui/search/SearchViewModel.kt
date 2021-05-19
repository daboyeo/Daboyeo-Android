package com.example.daboyeo_android.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daboyeo_android.http.interceptor.Result
import androidx.lifecycle.viewModelScope
import com.example.daboyeo_android.entity.home.ReportListData
import com.example.daboyeo_android.repository.SearchRepository
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel(){
    private val repository = SearchRepository()

    private val _reportsData = MutableLiveData<ReportListData>()
    val reportsData: LiveData<ReportListData> get() = _reportsData

    fun getReports(content: String) {
        viewModelScope.launch {
            when(val result = repository.getSearchTimeLine(content)) {
                is Result.Success -> {
                    _reportsData.value = result.data
                }
                is Result.Error -> {
                    Log.e("", result.exception)
                }
            }
        }
    }
}