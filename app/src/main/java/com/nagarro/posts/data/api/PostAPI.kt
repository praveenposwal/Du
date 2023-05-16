package com.nagarro.posts.data.api

import com.nagarro.posts.data.model.PostModel
import retrofit2.Response
import retrofit2.http.GET

interface PostAPI {

    @GET("/posts")
    suspend fun getPosts() : Response<List<PostModel>>

}