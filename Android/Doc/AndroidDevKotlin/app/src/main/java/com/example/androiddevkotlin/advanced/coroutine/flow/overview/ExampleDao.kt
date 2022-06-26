package com.example.androiddevkotlin.advanced.coroutine.flow.overview

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ExampleDao {
//    @Query("SELECT * FROM Example")
    abstract fun getExamples(): Flow<List<ArticleHeadline>>
}