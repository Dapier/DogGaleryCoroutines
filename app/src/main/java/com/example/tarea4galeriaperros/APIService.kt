package com.example.tarea4galeriaperros

import retrofit2.http.GET
import retrofit2.http.Url

//Create method to access to dog API
interface APIService {
    @GET
    suspend fun getDogsByBreeds(@Url url:String):retrofit2.Response<DogResponse>
}