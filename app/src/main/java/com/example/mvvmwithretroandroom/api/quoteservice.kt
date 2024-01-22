package com.example.mvvmwithretroandroom.api

import com.example.mvvmwithretroandroom.models.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface quoteservice {

    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page:Int):Response<QuoteList>
}