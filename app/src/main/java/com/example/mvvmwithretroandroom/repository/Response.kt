package com.example.mvvmwithretroandroom.repository

import com.example.mvvmwithretroandroom.models.QuoteList


sealed class Response<T>(val data:T?=null,val errorMessage: String?=null){

    class Loading<T>:Response<T>()
    class Sucess<T>(data: T?=null):Response<T>(data = data)
    class Error<T>(errorMessage: String):Response<T>(errorMessage=errorMessage)

}
