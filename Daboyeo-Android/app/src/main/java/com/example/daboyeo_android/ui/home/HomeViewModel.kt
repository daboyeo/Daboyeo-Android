package com.example.daboyeo_android.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daboyeo_android.entity.home.ReportData
import com.example.daboyeo_android.entity.home.ReportListData
import com.example.daboyeo_android.repository.HomeRepository
import kotlinx.coroutines.launch
import com.example.daboyeo_android.http.interceptor.Result
import com.example.daboyeo_android.ui.home.adapter.ReportsAdapter

class HomeViewModel : ViewModel() {
    private val repository = HomeRepository()
    private val TAG = "HomeViewModel"
    val mAdapter = ReportsAdapter()

    private val _reportsData = MutableLiveData<ReportListData>()
    private val reportsData: LiveData<ReportListData> get() = _reportsData

    init {
        getTimeLine()
    }

    private fun getTimeLine() {
        viewModelScope.launch {
            when (val result = repository.getTimeLine()) {
                is Result.Success -> {
                    _reportsData.value = result.data
                    mAdapter.setList(reportsData.value!!.reports)
                }
                is Result.Error -> {
                    Log.e(TAG, result.exception)
                }
            }

        }
    }

}