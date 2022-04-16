package com.androiddevs.mvvmnewsapp.repository

import com.androiddevs.mvvmnewsapp.api.RetrofitInstance
import com.androiddevs.mvvmnewsapp.db.ArticleDatabase

class NewsRepository(
    val db : ArticleDatabase
) {
    /**
     * To get the breaking news
     *
     * @param countryCode
     * @param pageNumber
     */
    suspend fun getBreakingNews(countryCode : String, pageNumber : Int) =
        RetrofitInstance.api.getBreakingNews(
            countryCode,
            pageNumber)

    /**
     * To search a news
     *
     * @param searchQuery
     * @param pageNumber
     */
    suspend fun searchNews(searchQuery : String, pageNumber : Int) =
        RetrofitInstance.api.searchForNews(
            searchQuery,
            pageNumber
        )
}