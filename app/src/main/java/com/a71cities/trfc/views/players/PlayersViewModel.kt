package com.a71cities.trfc.views.players

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.a71cities.currencyconverter.extras.BaseViewModel
import com.a71cities.trfc.api.RetrofitClient
import com.a71cities.trfc.utils.getErrorResponse
import com.a71cities.trfc.views.players.models.PlayersResponse
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class PlayersViewModel : BaseViewModel() {

    val passedId: MutableLiveData<String> = MutableLiveData()
    val playerData: MutableLiveData<List<PlayersResponse.Data>> = MutableLiveData()


    fun getPlayers() {
        loader.value = true

        viewModelScope.launch {
            try {
                val call = RetrofitClient.apiService.getPLayers(passedId.value!!)
                if (call.status == 200) {
                    loader.value = false

                    playerData.value = call.data!!

                } else {
                    loader.value = false
                }

            } catch (e: Exception) {
                e.printStackTrace()
                loader.value = false
                showAlertTxt.value = getErrorResponse(JSONObject((e as? HttpException)?.response()?.errorBody()?.string()))
            }
        }
    }
}