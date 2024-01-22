package com.example.mvvmwithretroandroom

import android.app.Application
import androidx.constraintlayout.widget.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.mvvmwithretroandroom.api.RetrofitHelper
import com.example.mvvmwithretroandroom.api.quoteservice
import com.example.mvvmwithretroandroom.db.QuoteDao
import com.example.mvvmwithretroandroom.db.QuoteDatabase
import com.example.mvvmwithretroandroom.repository.quoteRepository
import com.example.mvvmwithretroandroom.worker.QuoteWorker
import okio.Timeout
import java.util.concurrent.TimeUnit

class QuoteApplication:Application() {

 lateinit var quoteRepository: quoteRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
        setUpWorker()
    }

    private fun setUpWorker(){
        val constraint =androidx.work.Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workRequest = PeriodicWorkRequest.Builder(QuoteWorker::class.java,15,TimeUnit.MINUTES)
            .setConstraints(constraint)
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)

    }

    private fun initialize(){
        val quoteservice = RetrofitHelper.getInstance().create(quoteservice::class.java)
        val database = QuoteDatabase.getInstance(applicationContext)
        quoteRepository = quoteRepository(quoteservice,database,applicationContext)
    }
}