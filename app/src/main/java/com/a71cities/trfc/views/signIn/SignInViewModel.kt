package com.a71cities.trfc.views.signIn

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a71cities.currencyconverter.extras.BaseViewModel
import com.a71cities.trfc.api.RetrofitClient
import com.a71cities.trfc.extras.Pref
import com.a71cities.trfc.utils.getErrorResponse
import com.a71cities.trfc.utils.isValidEmail
import com.a71cities.trfc.views.signIn.model.LoginData
import com.google.gson.Gson
import kotlinx.coroutines.launch

class SignInViewModel : BaseViewModel() {

    val email: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val logInData: MutableLiveData<LoginData> = MutableLiveData()

    fun submitData(v: View?){
        if (isValid()) {
            loader.value = true

            viewModelScope.launch {
                try {

                    val rawData = HashMap<String,String>()
                    rawData["email"] = email.value!!
                    rawData["password"] = password.value!!

                    val call = RetrofitClient.apiService.signIn(rawData)

                    if (call.status == 200) {
                        logInData.value = call.data!!
                        Pref.profile = call.data

                        loader.value = false
                    } else {
                        println("DATA: "+Gson().toJson(call).toString())
//                        val error = getErrorResponse(Gson().toJson(call).toString())
//                        showAlertTxt.value = error.data

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