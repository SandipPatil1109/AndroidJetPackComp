package com.example.mvvmwithretroandroom.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmwithretroandroom.api.quoteservice
import com.example.mvvmwithretroandroom.db.QuoteDatabase
import com.example.mvvmwithretroandroom.models.QuoteList
import com.example.mvvmwithretroandroom.utils.NetworkUtils

class quoteRepository(
    private val quoteservice: quoteservice,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {

    private val quotesListLiveData = MutableLiveData<Response<QuoteList>>()

    val quotes: LiveData<Response<QuoteList>>
        get() = quotesListLiveData

    suspend fun getQuotes(page: Int) {
        if (NetworkUtils.isNetworkConnected(applicationContext)) {
            try {
                val result = quoteservice.getQuotes(page)
                if (result?.body() != null) {
                    quotesListLiveData.postValue(Response.Sucess(result.body()))
                    quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
                }
            } catch (e: Exception) {
                quotesListLiveData.postValue(Response.Error(e.message.toString()))
            }

        } else {
            val quotes = quoteDatabase.quoteDao().getQuotesList()
            val quoteList = QuoteList(1, 1, 1, quotes, 1, 1)
            quotesListLiveData.postValue(Response.Sucess(quoteList))
        }
    }

    suspend fun getQuoteBackground() {
        val randomNum = (Math.random() * 10).toInt()
        val result = quoteservice.getQuotes(randomNum)
        if (result.body() != null) {
            quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
        }
    }

}