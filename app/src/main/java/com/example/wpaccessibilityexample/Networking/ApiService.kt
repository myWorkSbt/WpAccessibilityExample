package com.example.wpaccessibilityexample.Networking

import com.example.wpaccessibilityexample.data.Model
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    fun posts(): Call<Model?>?
}