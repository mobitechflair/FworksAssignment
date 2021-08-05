package com.example.fworks.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class TredingGifImage : Serializable{

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("bitly_gif_url")
    @Expose
    var bitly_gif_url: String? = null

    @SerializedName("bitly_url")
    @Expose
    var bitly_url: String? = null

    @SerializedName("embed_url")
    @Expose
    var embed_url: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("rating")
    @Expose
    var rating: String? = null

    @SerializedName("import_datetime")
    @Expose
    var import_datetime: String? = null

    @SerializedName("images")
    @Expose
    var images: ImgAttributes? = null


}