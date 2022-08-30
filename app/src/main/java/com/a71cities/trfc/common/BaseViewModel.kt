package com.a71cities.currencyconverter.extras

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {

    val loader: MutableLiveData<Boolean> = MutableLiveData()
}