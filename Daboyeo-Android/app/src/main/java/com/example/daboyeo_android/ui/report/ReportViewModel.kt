package com.example.daboyeo_android.ui.report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daboyeo_android.repository.ReportRepository
import kotlinx.coroutines.launch

class ReportViewModel : ViewModel() {

    private val repository = ReportRepository()

    fun clickSympathy() {

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
            val result = repository.addComment(hashMap)
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

    }

}