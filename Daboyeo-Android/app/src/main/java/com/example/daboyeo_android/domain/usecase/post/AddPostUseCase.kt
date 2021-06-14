package com.example.daboyeo_android.domain.usecase.post

import com.example.daboyeo_android.domain.repository.AddPostRepository
import org.json.JSONArray

class AddPostUseCase(private val addPostRepository: AddPostRepository) {
    suspend fun execute(tags: JSONArray, images: JSONArray, hashMap: HashMap<String, String>) =
        addPostRepository.addPost(tags, images, hashMap)
}