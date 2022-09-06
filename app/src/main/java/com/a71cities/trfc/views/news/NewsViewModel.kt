package com.a71cities.trfc.views.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a71cities.currencyconverter.extras.BaseViewModel
import com.a71cities.trfc.api.RetrofitClient
import com.a71cities.trfc.utils.getErrorResponse
import com.a71cities.trfc.views.news.model.NewsReponse
import com.a71cities.trfc.views.news.model.ReactionResponse
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class NewsViewModel : BaseViewModel() {

    val newsArray: MutableLiveData<List<NewsReponse.Data>> = MutableLiveData()

    init {
        getNewsData()
    }

    fun getNewsData() {
        viewModelScope.launch {
            loader.value = true
            try {
                val call = RetrofitClient.apiService.getNews()

                if (call.status == 200){
                    newsArray.value = call.data!!
                    loader.value = false
                }

            }catch (e: Exception) {
                e.printStackTrace()
                loader.value = false
            }
        }
    }

    fun likeUnlike(reaction: String,id: String) {
        viewModelScope.launch {
            try {
                RetrofitClient.apiService.likeUnlike(reaction, id)
            } catch (e: HttpException) {
                e.printStackTrace()
                loader.value = false
                showAlertTxt.value = getErrorResponse(JSONObject((e as? HttpException)?.response()?.errorBody()?.string()))
            }


        }
    }
}