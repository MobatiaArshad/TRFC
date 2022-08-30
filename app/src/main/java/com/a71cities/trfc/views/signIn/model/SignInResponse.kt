package com.a71cities.trfc.views.signIn.model


import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("data")
    var `data`: LoginData?,
    @SerializedName("status")
    var status: Int?
)

data class LoginData(
    @SerializedName("token")
    var token: String?,
    @SerializedName("user")
    var user: User?
) {
    data class User(
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
