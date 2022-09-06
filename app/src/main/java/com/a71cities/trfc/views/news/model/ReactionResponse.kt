package com.a71cities.trfc.views.news.model


import com.google.gson.annotations.SerializedName

data class ReactionResponse(
    @SerializedName("data")
    var `data`: Data?,
    @SerializedName("status")
    var status: Int?
) {
    data class Data(
        @SerializedName("content")
        var content: String?,
        @SerializedName("createdAt")
        var createdAt: String?,
        @SerializedName("_id")
        var id: String?,
        @SerializedName("image")
        var image: Image?,
        @SerializedName("likedBy")
        var likedBy: List<String?>?,
        @SerializedName("likes")
        var likes: Int?,
        @SerializedName("title")
        var title: String?,
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