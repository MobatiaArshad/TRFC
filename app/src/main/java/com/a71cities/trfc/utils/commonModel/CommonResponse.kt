package com.a71cities.trfc.utils.commonModel


import com.google.gson.annotations.SerializedName

data class CommonResponse(
    @SerializedName("data")
    var `data`: String?,
    @SerializedName("status")
    var status: Int?
)