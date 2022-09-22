package com.a71cities.trfc.views.academy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a71cities.currencyconverter.extras.BaseViewModel
import com.a71cities.trfc.api.RetrofitClient
import com.a71cities.trfc.utils.getErrorResponse
import com.a71cities.trfc.views.academy.model.AcademyResponse
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class AcademyViewModel : BaseViewModel() {

    val data: MutableLiveData<List<AcademyResponse.Data>> = MutableLiveData()

    init {
        getAcademy()
    }

    private fun getAcademy() {
        loader.value = true

        viewModelScope.launch {
            try {
                val call = RetrofitClient.apiService.getAcademy()

                if (call.status == 200) {
                    data.value = call.data!!
                    loader.value = false
                } else {
                    loader.value = false
                }

            }catch (e: HttpException) {
                e.printStackTrace()
                loader.value = false
                showAlertTxt.value = getErrorResponse(JSONObject((e as? HttpException)?.response()?.errorBody()?.string()))
            }
        }
    }

}