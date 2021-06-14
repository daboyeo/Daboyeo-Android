package com.example.daboyeo_android.data.repository.search

import com.example.daboyeo_android.data.api.service.TimelineService
import com.example.daboyeo_android.data.model.home.PostListData
import com.example.daboyeo_android.presentation.util.DaboyeoApplication.Companion.pref
import retrofit2.Response
import javax.inject.Inject

class SearchDataSourceImpl (
    private val timelineService: TimelineService
): SearchDataSource {
    private val token = pref!!.getToken()

    override suspend fun getSearchTimeLine(content: String): Response<PostListData> {
        return timelineService.getSearchTimeLine(token, content)
    }
}