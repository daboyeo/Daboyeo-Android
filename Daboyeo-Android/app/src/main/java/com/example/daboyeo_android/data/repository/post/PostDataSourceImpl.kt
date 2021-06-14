package com.example.daboyeo_android.data.repository.post

import com.example.daboyeo_android.data.api.service.FileService
import com.example.daboyeo_android.data.api.service.PostService
import com.example.daboyeo_android.data.model.home.DetailPostData
import com.example.daboyeo_android.presentation.util.DaboyeoApplication
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import org.json.JSONArray
import retrofit2.Response
import java.io.File

class PostDataSourceImpl (
        private val postService: PostService,
        private val fileService: FileService
): PostDataSource {
    private val token = DaboyeoApplication.pref!!.getToken()

    override suspend fun like(reportId: HashMap<String, Int>): Response<Boolean> {
        return postService.like(token, reportId)
    }

    override suspend fun addComment(hashMap: HashMap<String, String>): Response<Unit> {
        return postService.addComment(token, hashMap)
    }

    override suspend fun deleteComment(hashMap: HashMap<String, String>): Response<Unit> {
        return postService.deleteComment(token, hashMap)
    }

    override suspend fun deletePost(reportId: JsonObject): Response<Unit> {
        return postService.deletePost(token, reportId)
    }

    override suspend fun getImage(uuid: String): Response<File> {
        return fileService.getFile(uuid)
    }

    override suspend fun uploadFile(image: MultipartBody.Part): Response<String> {
        return fileService.uploadFile(image)
    }

    override suspend fun getDetailPost(id: Int): Response<DetailPostData> {
        return postService.getDetailPost(token, id)
    }

}