package com.example.posts.data.model

import com.squareup.moshi.Json

data class PostModel(
    @Json(name = "userId")
    var userId: Int = 0,
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "title")
    var title: String = "",
    @Json(name = "body")
    var body: String = "",
    @Json(name = "isFav")
    var isFav : Boolean = false
)