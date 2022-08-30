package com.a71cities.currencyconverter.extras

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a71cities.trfc.extras.SingleLiveEvent

open class BaseViewModel: ViewModel() {

    val loader: MutableLiveData<Boolean> = MutableLiveData()
    val showAlertTxt = SingleLiveEvent<String>()
    val showAlertRes = SingleLiveEvent<Int>()
}