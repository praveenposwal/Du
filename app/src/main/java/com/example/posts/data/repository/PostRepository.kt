package com.example.posts.data.repository

import com.example.posts.data.api.PostAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepository @Inject constructor(private val postApi : PostAPI) {

    suspend fun getAlbum() = withContext(Dispatchers.IO){postApi.getPosts()}

}