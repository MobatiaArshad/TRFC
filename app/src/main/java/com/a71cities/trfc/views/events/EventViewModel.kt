package com.a71cities.trfc.views.events

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a71cities.currencyconverter.extras.BaseViewModel
import com.a71cities.trfc.api.RetrofitClient
import com.a71cities.trfc.utils.getErrorResponse
import com.a71cities.trfc.views.events.model.MatchResponse
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class EventViewModel : BaseViewModel() {

    val matchData: MutableLiveData<List<MatchResponse.Data>> = MutableLiveData()

    init {
        getMatches()
    }

    private fun getMatches() {
        loader.value = true

        viewModelScope.launch {
            try {
                val call = RetrofitClient.apiService.getMatches()

                if (call.status == 200) {
                    loader.value = false
                    matchData.value = call.data!!

                } else {
                    loader.value = false
                }

            } catch (e: HttpException) {
                e.printStackTrace()
                loader.value = false
                showAlertTxt.value = getErrorResponse(JSONObject((e as? HttpException)?.response()?.errorBody()?.string()))
            }
        }
    }
}