package com.a71cities.trfc.views.devisions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a71cities.currencyconverter.extras.BaseViewModel
import com.a71cities.trfc.api.RetrofitClient
import com.a71cities.trfc.utils.getErrorResponse
import com.a71cities.trfc.views.devisions.model.DevisionReponse
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class DevisionsViewModel : BaseViewModel() {

    val divisionArray: MutableLiveData<List<DevisionReponse.Data>> = MutableLiveData()

    init {
        viewModelScope.launch {
            loader.value = true
            try {
                val call = RetrofitClient.apiService.getDivisions()

                if (call.status == 200) {
                    divisionArray.value = call.data!!
                    loader.value = false

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