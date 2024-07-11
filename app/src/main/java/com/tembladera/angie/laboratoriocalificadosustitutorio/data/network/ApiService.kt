package com.tembladera.angie.laboratoriocalificadosustitutorio.data.network

import com.tembladera.angie.laboratoriocalificadosustitutorio.data.model.Post
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}