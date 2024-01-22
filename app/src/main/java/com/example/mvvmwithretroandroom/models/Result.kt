package com.example.mvvmwithretroandroom.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote",primaryKeys = ["_id"])
data class Result(
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
)