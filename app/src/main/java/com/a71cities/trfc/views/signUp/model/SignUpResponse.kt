package com.a71cities.trfc.views.signUp.model


import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("data")
    var `data`: Data?,
    @SerializedName("status")
    var status: Int?
) {
    data class Data(
        @SerializedName("appUser")
        var appUser: Boolean?,
        @SerializedName("createdAt")
        var createdAt: String?,
        @SerializedName("email")
        var email: String?,
        @SerializedName("_id")
        var id: String?,
        @SerializedName("password")
        var password: String?,
        @SerializedName("updatedAt")
        var updatedAt: String?,
        @SerializedName("userName")
        var userName: String?,
        @SerializedName("__v")
        var v: Int?
    )
}