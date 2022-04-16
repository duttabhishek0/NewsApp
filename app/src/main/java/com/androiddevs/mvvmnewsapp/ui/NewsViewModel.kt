package com.androiddevs.mvvmnewsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.mvvmnewsapp.models.NewsResponse
import com.androiddevs.mvvmnewsapp.repository.NewsRepository
import com.androiddevs.mvvmnewsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository : NewsRepository
) : ViewModel() {
    val breakingNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val breakingNewsPage = 1

    val searchNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val searchNewsPage = 1

    init{
        getBreakingNews("in")
    }
    /**
     * viewModelScope.launch makes sure that the
     * coroutine stays as long as the viewModel is there
     *
     * @param countryCode
     * @return
     */
    fun getBreakingNews(countryCode : String) = viewModelScope.launch{
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode,breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }



    fun searchNews(searchQuery : String) = viewModelScope.launch{
        searchNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews(searchQuery,searchNewsPage)
        searchNews.postValue(handleSearchResponse(response))
    }
    /**
     * Method to decide whether to emit
     * Success or Error state in live data
     *
     * @param response
     * @return Resource<NewsResponse>
     */
    private fun handleBreakingNewsResponse(
        response: Response<NewsResponse>
    ) : Resource<NewsResponse> {
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    /**
     * Method to handle search query
     *
     * @param response
     * @return Resource<NewsResponse>
     */
    private fun handleSearchResponse(
        response: Response<NewsResponse>
    ) : Resource<NewsResponse> {
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}