package com.example.mvvmwithretroandroom.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmwithretroandroom.models.Result

@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuotes(quotes:List<Result>)

    @Query("Select * from quote")
    suspend fun getQuotesList():List<Result>
}