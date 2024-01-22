package com.example.mvvmwithretroandroom.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmwithretroandroom.models.QuoteList
import com.example.mvvmwithretroandroom.repository.Response
import com.example.mvvmwithretroandroom.repository.quoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: quoteRepository):ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuotes(1)
        }
    }

    val quotes:LiveData<Response<QuoteList>>
        get()=repository.quotes
}