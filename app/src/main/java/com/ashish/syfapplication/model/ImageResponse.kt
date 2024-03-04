package com.ashish.syfapplication.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class ImageResponse : Serializable {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("breeds")
    var breeds: ArrayList<Breeds> = arrayListOf()

    @SerializedName("width")
    var width: Int? = null

    @SerializedName("height")
    var height: Int? = null
}