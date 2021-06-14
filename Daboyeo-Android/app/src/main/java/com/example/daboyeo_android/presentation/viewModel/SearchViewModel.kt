package com.example.daboyeo_android.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daboyeo_android.data.model.home.PostListData
import com.example.daboyeo_android.domain.usecase.search.SearchUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchViewModel(private val searchUseCase: SearchUseCase) : ViewModel(){
    private val _reportsData = MutableLiveData<PostListData>()
    val reportsData: LiveData<PostListData> get() = _reportsData

    fun getPosts(content: String) {
        viewModelScope.launch {
            try{
                val result = searchUseCase.execute(content)
                _reportsData.value = result.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}