package com.example.mvvmwithretroandroom.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.mvvmwithretroandroom.QuoteApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteWorker(private val context: Context, parameters: WorkerParameters):Worker(context,parameters) {
    override fun doWork(): Result {
       val repository = (context as QuoteApplication).quoteRepository
        CoroutineScope(Dispatchers.IO).launch {
            repository.getQuoteBackground()

        }
        return Result.success()
    }

}