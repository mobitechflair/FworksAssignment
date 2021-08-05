package com.example.fworks.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class GifResponse : Serializable {
    @SerializedName("data")
    @Expose
    var data: ArrayList<TredingGifImage>? = null
}