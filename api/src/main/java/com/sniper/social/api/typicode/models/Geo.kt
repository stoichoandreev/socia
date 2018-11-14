package com.sniper.social.api.typicode.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Geo: Serializable {

    @SerializedName("lat")
    @Expose
    lateinit var lat: String

    @SerializedName("lng")
    @Expose
    lateinit var lng: String

}
