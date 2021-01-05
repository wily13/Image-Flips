package com.wily.imageapp.api

import com.wily.imageapp.data.source.remote.response.ImageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("get_memes")
    fun getAllImage(): Call<ImageResponse>
}