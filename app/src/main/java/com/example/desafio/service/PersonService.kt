package com.example.desafio.service

import com.example.desafio.model.Responce
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonService {

    @GET("character/")
    fun getPersons(@Query("page") page: Int): Call<Responce>
}