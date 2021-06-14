package com.example.daboyeo_android.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daboyeo_android.data.model.home.DetailPostData
import com.example.daboyeo_android.domain.repository.PostRepository
import kotlinx.coroutines.launch
import com.example.daboyeo_android.domain.usecase.post.*
import com.google.gson.JsonObject
import java.io.File

class PostViewModel(
    private val addCommentUseCase: AddCommentUseCase,
    private val deleteCommentUseCase: DeleteCommentUseCase,
    private val deletePostUseCase: DeletePostUseCase,
    private val getDetailPostUseCase: GetDetailPostUseCase,
    private val getImageUseCase: GetImageUseCase,
    private val likeUseCase: LikeUseCase
) : ViewModel() {
    private val _detailReport = MutableLiveData<DetailPostData>()
    val detailPost: LiveData<DetailPostData> get() = _detailReport
    var uuid : List<File> = listOf()

    fun getDetailReport(id: Int) {
        viewModelScope.launch {
            val result = getDetailPostUseCase.execute(id)
            _detailReport.value = result.data
        }
    }

    fun like(reportId: Int) {
        var hashMap = HashMap<String, Int>()
        hashMap["reportId"] = reportId
        viewModelScope.launch {
            likeUseCase.execute(hashMap)
        }
    }

    fun addComment(comment: String, reportId: Int) {
        var hashMap: HashMap<String, String> = HashMap()

        hashMap.apply {
            put("report_id", reportId.toString())
            put("content", comment)
        }

        viewModelScope.launch {
            addCommentUseCase.execute(hashMap)
        }

    }

    fun deleteComment(reportId: String) {
        var hashMap : HashMap<String, String> = HashMap()
        hashMap.put("report_id", reportId)

        viewModelScope.launch {
            deleteCommentUseCase.execute(hashMap)
        }
    }

    fun deletePost(reportId: Int) {
        var json = JsonObject()
        json.addProperty("report_Id", reportId)
        viewModelScope.launch {
            deletePostUseCase.execute(json)
        }
    }

    fun getImage(uuid: List<String>) {
        viewModelScope.launch {
            for(i in 0 until uuid.size) {
                getImageUseCase
            }
        }
    }
}