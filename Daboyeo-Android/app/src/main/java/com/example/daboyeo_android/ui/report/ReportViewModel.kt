package com.example.daboyeo_android.ui.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daboyeo_android.entity.home.DetailReportData
import com.example.daboyeo_android.repository.ReportRepository
import kotlinx.coroutines.launch
import com.example.daboyeo_android.http.interceptor.Result

class ReportViewModel : ViewModel() {

    private val repository = ReportRepository()

    private val _detailReport = MutableLiveData<DetailReportData>()
    val detailReport: LiveData<DetailReportData> get() = _detailReport

    fun getDetailReport(reportId: Int) {
        viewModelScope.launch {
            when(val result = repository.getDetailReport(reportId)) {
                is Result.Success -> {
                    if(result.code == 200) {
                        _detailReport.value = result.data
                    }
                }
                is Result.Error -> {

                }
            }
        }
    }

    fun clickSympathy(reportId: Int) {
        //repository.clickSympathy()
    }

    fun modifyReport() {

    }

    fun addComment(comment: String, reportId: String) {
        var hashMap: HashMap<String, String> = HashMap()

        hashMap.apply {
            put("report_id", reportId)
            put("content", comment)
        }

        viewModelScope.launch {
            when(val result = repository.addComment(hashMap)) {

            }
        }

    }

    fun deleteComment(reportId: String) {
        var hashMap : HashMap<String, String> = HashMap()
        hashMap.put("report_id", reportId)

        viewModelScope.launch {
            val result = repository.deleteComment(hashMap)
        }
    }

    fun deleteReport() {
        viewModelScope.launch {

        }
    }

}