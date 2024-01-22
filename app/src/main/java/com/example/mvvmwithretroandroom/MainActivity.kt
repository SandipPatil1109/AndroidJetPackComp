package com.example.mvvmwithretroandroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmwithretroandroom.api.RetrofitHelper
import com.example.mvvmwithretroandroom.api.quoteservice
import com.example.mvvmwithretroandroom.db.QuoteDatabase
import com.example.mvvmwithretroandroom.repository.Response
import com.example.mvvmwithretroandroom.repository.quoteRepository
import com.example.mvvmwithretroandroom.viewModels.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository =(application as QuoteApplication).quoteRepository
        viewModel = ViewModelProvider(this,MainViewModelFactory(repository))[MainViewModel::class.java]
        viewModel.quotes.observe(this, Observer { it ->

            when(it){
                is Response.Loading ->{

                }
                is Response.Sucess ->{
                    it.data?.let {
                     Toast.makeText(this@MainActivity,it.results.size.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
                is Response.Error ->{
                    Toast.makeText(this@MainActivity,it.toString(),Toast.LENGTH_SHORT).show()

                }
            }
        })
    }
}