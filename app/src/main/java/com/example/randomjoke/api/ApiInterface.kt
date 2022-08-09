package com.example.randomjoke.api

import com.example.randomjoke.models.Joke
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("api?format=json")
    suspend fun getJoke(): Response<Joke>
}