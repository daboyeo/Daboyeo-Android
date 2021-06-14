package com.example.daboyeo_android.data.repository.home

import com.example.daboyeo_android.data.api.service.TimelineService
import com.example.daboyeo_android.data.model.home.DetailPostData
import com.example.daboyeo_android.data.model.home.PostListData
import com.example.daboyeo_android.presentation.util.DaboyeoApplication
import org.json.JSONArray
import retrofit2.Response
import javax.inject.Inject

class HomeDataSourceImpl (
    private val timelineService: TimelineService
): HomeDataSource {
    private val token = DaboyeoApplication.pref!!.getToken()

    override suspend fun getTimeLine(): Response<PostListData> {
        return timelineService.getTimeLine(token)
    }

    override suspend fun addPost(
            tags: JSONArray,
            images: JSONArray,
            hashMap: HashMap<String, String>
    ): Response<String> {
        return timelineService.addPost(token, tags, images, hashMap)
    }
}