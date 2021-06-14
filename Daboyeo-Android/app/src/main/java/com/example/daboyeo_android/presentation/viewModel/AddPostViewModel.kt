package com.example.daboyeo_android.presentation.viewModel

import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daboyeo_android.domain.repository.AddPostRepository
import com.example.daboyeo_android.presentation.util.ContentUriRequestBody
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import com.example.daboyeo_android.data.api.interceptor.Result
import com.example.daboyeo_android.domain.usecase.post.AddPostUseCase
import com.example.daboyeo_android.domain.usecase.profile.UploadImageUseCase
import com.google.gson.JsonArray
import org.json.JSONArray

class AddPostViewModel(
    private val addPostUseCase: AddPostUseCase,
    private val uploadImageUseCase: UploadImageUseCase
) : ViewModel() {
    var imageUriList = ArrayList<Uri>()

    fun addPost(content: String, location: String, contentResolver: ContentResolver) {
        val tagList = returnTagList(content)
        val hashMap = returnHashMap(content, location)
        val imageList = returnImageList(contentResolver)

        viewModelScope.launch {
            addPostUseCase.execute(tagList, imageList, hashMap)
        }
    }

    private fun returnTagList(content: String): JSONArray {
        val tagList = changeHashtag(content)
        var tags = JSONArray()
        for (i in 0 until tagList.size) {
            tags.put(tagList[i])
        }

        return tags
    }

    private fun returnImageList(contentResolver: ContentResolver): JSONArray {
        val imageList = changeType(contentResolver)
        var uuidList = ArrayList<String>()

        viewModelScope.launch {
            for (i in 0 until imageList.size) {
                val result = uploadImageUseCase.execute(imageList[i])
                uuidList.add(result.data.toString())

            }
        }

        var images = JSONArray()
        for (i in 0 until uuidList.size) {
            images.put(uuidList[i])
        }

        return images
    }



    private fun returnHashMap(content: String, location: String): HashMap<String, String> {
        val hashMap = HashMap<String, String>()

        hashMap["content"] = content
        hashMap["location"] = location

        return hashMap
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