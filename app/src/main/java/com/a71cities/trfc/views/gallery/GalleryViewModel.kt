package com.a71cities.trfc.views.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a71cities.currencyconverter.extras.BaseViewModel
import com.a71cities.trfc.api.RetrofitClient
import com.a71cities.trfc.views.gallery.model.GalleryResponse
import kotlinx.coroutines.launch

class GalleryViewModel : BaseViewModel() {

    val galleryArray: MutableLiveData<List<GalleryResponse.Data>> = MutableLiveData()

    init {
        getGalleryData()
    }

    private fun getGalleryData() {
        loader.value = true
        viewModelScope.launch {
            try {
                val call = RetrofitClient.apiService.getGallery()
                if (call.status == 200) {
                    galleryArray.value = call.data!!
                }
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}