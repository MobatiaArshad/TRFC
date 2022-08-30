package com.a71cities.trfc.views.devisions.model


import com.google.gson.annotations.SerializedName

data class DevisionReponse(
    @SerializedName("data")
    var `data`: List<Data>?,
    @SerializedName("status")
    var status: Int?
) {
    data class Data(
        @SerializedName("createdAt")
        var createdAt: String?,
        @SerializedName("description")
        var description: String?,
        @SerializedName("_id")
        var id: String?,
        @SerializedName("image")
        var image: Image?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("status")
        var status: String?,
        @SerializedName("teamLength")
        var teamLength: Int?,
        @SerializedName("updatedAt")
        var updatedAt: String?,
        @SerializedName("__v")
        var v: Int?
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