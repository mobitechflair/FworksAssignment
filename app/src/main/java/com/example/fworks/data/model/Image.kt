package com.example.fworks.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Image : Serializable {

    @SerializedName("height")
    @Expose
    var height: String? = null

    @SerializedName("width")
    @Expose
    var type: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null
}