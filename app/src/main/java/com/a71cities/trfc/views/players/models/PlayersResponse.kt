package com.a71cities.trfc.views.players.models


import com.google.gson.annotations.SerializedName

data class PlayersResponse(
    @SerializedName("data")
    var `data`: List<Data>?,
    @SerializedName("status")
    var status: Int?
) {
    data class Data(
        @SerializedName("age")
        var age: Int?,
        @SerializedName("createdAt")
        var createdAt: String?,
        @SerializedName("email")
        var email: String?,
        @SerializedName("height")
        var height: Int?,
        @SerializedName("_id")
        var id: String?,
        @SerializedName("image")
        var image: Image?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("phone")
        var phone: String?,
        @SerializedName("place")
        var place: String?,
        @SerializedName("position")
        var position: String?,
        @SerializedName("status")
        var status: String?,
        @SerializedName("team")
        var team: String?,
        @SerializedName("updatedAt")
        var updatedAt: String?,
        @SerializedName("__v")
        var v: Int?,
        @SerializedName("weight")
        var weight: Int?
    ) {
        data class Image(
            @SerializedName("destination")
            var destination: String?,
            @SerializedName("encoding")
            var encoding: String?,
            @SerializedName("fieldname")
            var fieldname: String?,
            @SerializedName("filename")
            var filename: String?,
            @SerializedName("mimetype")
            var mimetype: String?,
            @SerializedName("originalname")
            var originalname: String?,
            @SerializedName("path")
            var path: String?,
            @SerializedName("size")
            var size: Int?,
            @SerializedName("url")
            var url: String?
        )
    }
}