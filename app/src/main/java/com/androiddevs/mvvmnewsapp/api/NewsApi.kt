package com.androiddevs.mvvmnewsapp.api

import com.androiddevs.mvvmnewsapp.models.NewsResponse
import com.androiddevs.mvvmnewsapp.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    /**
     * Suspend fun to get a list
     * of breaking news
     *
     * @param countryCode
     * @param pageNumber
     * @param apiKey
     * @return NewsResponse
     */
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode : String="us",
        @Query("page")
        pageNumber : Int=1,
        @Query("apiKey")
        apiKey : String = API_KEY
    ) : Response<NewsResponse>


    /**
     * Suspend fun to get a
     * list of news based on a query
     *
     * @param searchQuery
     * @param pageNumber
     * @param apiKey
     * @return NewsResponse
     */
    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery : String,
        @Query("page")
        pageNumber : Int=1,
        @Query("apiKey")
        apiKey : String = API_KEY
    ) : Response<NewsResponse>
}