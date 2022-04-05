package com.androiddevs.mvvmnewsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.androiddevs.mvvmnewsapp.models.Article

@Dao
interface ArticleDao {

    /**
     * Insert an article into the db
     * Replace if already exists.
     * Return the Auto-generated
     * Primary Key.
     *
     * @param article
     * @return Primary Key : Long
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article) : Long


    /**
     * Query to get a list of
     * article as a live data
     * Don't use coroutine on query,
     * as it returns live data.
     *
     * @return List of Article : LiveData<List<Article>>
     */
    @Query("SELECT * FROM article")
    fun getAllArticle() : LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article : Article)
}