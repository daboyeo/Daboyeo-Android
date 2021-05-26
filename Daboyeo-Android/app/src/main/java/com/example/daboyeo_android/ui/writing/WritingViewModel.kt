package com.example.daboyeo_android.ui.writing

import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daboyeo_android.http.interceptor.Result
import com.example.daboyeo_android.repository.WritingRepository
import com.example.daboyeo_android.util.ContentUriRequestBody
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class WritingViewModel : ViewModel() {
    private val repository = WritingRepository()
    var imageUriList = ArrayList<Uri>()

    fun writingReport(content: String, location: String, contentResolver: ContentResolver) {
        val uuid = writingImage(contentResolver)
    }

    private fun writingImage(contentResolver: ContentResolver) {
        val imageList = changeType(contentResolver)
        var uuidList = ArrayList<Int>()

        viewModelScope.launch {
            for(i in 0 until imageList.size) {
                when(val result = repository.imageToServer(imageList[i])) {
                    is Result.Success -> {
                        if(result.code == 201)
                            uuidList.add(result.data)
                    }
                    is Result.Error -> {
                        Log.e("WritingViewModel", "fail")
                    }
                }
            }
        }
        //return uuidList
    }

    private fun changeType(contentResolver: ContentResolver): ArrayList<MultipartBody.Part> {
        var imageRequestList = ArrayList<MultipartBody.Part>()

        for (i in 0 until imageUriList.size) {
            val requestBody = ContentUriRequestBody(contentResolver, imageUriList[i])
            val body = MultipartBody.Part.createFormData("image", "imageUri", requestBody)
            imageRequestList.add(body)
        }

        return imageRequestList
    }

    private fun changeHashtag(text: String): MutableList<String> {
        var isCharacter = false
        var index = 0
        val res = mutableListOf<Char>()

        while (text.length >= index + 1) {
            if (text[index] == '#') isCharacter = true
            if (text[index] == ' ') isCharacter = false
            if (isCharacter) res.add(text[index])

            index++
        }

        val hashTags = mutableListOf<String>()
        var count = -1
        if ('#' in res) {
            for (i in res) {
                if (i == '#') {
                    count++
                    hashTags.add("")
                } else {
                    hashTags[count] += i.toString()
                }
            }
        }

        return hashTags
    }

}