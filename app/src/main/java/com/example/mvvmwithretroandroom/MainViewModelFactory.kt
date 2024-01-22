package com.example.mvvmwithretroandroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmwithretroandroom.repository.quoteRepository
import com.example.mvvmwithretroandroom.viewModels.MainViewModel

class MainViewModelFactory(private val repository: quoteRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}