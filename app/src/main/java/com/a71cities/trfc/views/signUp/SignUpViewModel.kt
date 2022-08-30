package com.a71cities.trfc.views.signUp

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.a71cities.currencyconverter.extras.BaseViewModel
import com.a71cities.trfc.api.RetrofitClient
import com.a71cities.trfc.extras.SingleLiveEvent
import com.a71cities.trfc.utils.getErrorResponse
import com.a71cities.trfc.utils.isValidEmail
import com.a71cities.trfc.views.signUp.model.SignUpResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class SignUpViewModel : BaseViewModel() {

    val userName: MutableLiveData<String> = MutableLiveData()
    val email: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val registerData: MutableLiveData<SignUpResponse.Data> = MutableLiveData()

    fun submitData(v: View?) {
        if (isValid()) {
            loader.value = true

            viewModelScope.launch {

                try {

                    val rawData = HashMap<String,String>()
                    rawData["userName"] = userName.value!!
                    rawData["email"] = email.value!!
                    rawData["password"] = password.value!!

                    val call = RetrofitClient.apiService.signUp(rawData)

                    if (call.status == 200) {
                        registerData.value = call.data!!

                        loader.value = false
                    } else {
                        val error = getErrorResponse(Gson().toJson(call).toString())
                        showAlertTxt.value = error.data

                        loader.value = false
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    loader.value = false
                }

            }
        }
    }

    private fun isValid(): Boolean {
        when {
            userName.value.isNullOrEmpty() -> {
                showAlertTxt.value = "Please enter your name"
            }
            email.value.isNullOrEmpty() -> {
                showAlertTxt.value = "Please enter your email"
            }
            !isValidEmail(email.value!!) -> {
                showAlertTxt.value = "Please enter a valid email"
            }
            password.value.isNullOrEmpty() -> {
                showAlertTxt.value = "Please enter a password"
            }
            password.value!!.length < 6 -> {
                showAlertTxt.value = "Password should be at least 6 character"
            }
            else -> return true
        }
        return false
    }
}