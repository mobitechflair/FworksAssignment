package com.example.fworks.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ImgAttributes : Serializable {

    @SerializedName("original")
    @Expose
    var orignal: Image? = null

    @SerializedName("preview_gif")
    @Expose
    var preview: Image? = null
}