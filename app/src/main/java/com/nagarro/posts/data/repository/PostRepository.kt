package com.nagarro.posts.data.repository

import com.nagarro.posts.data.api.PostAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepository @Inject constructor(private val postApi : PostAPI) {

    suspend fun getPosts() = withContext(Dispatchers.IO){postApi.getPosts()}

}