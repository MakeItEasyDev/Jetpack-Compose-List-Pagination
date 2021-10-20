package com.jetpack.composepagination.network

import com.jetpack.composepagination.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUserList(@Query("page") page: Int): UserResponse
}