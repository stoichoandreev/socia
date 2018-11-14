package com.sniper.social.api.typicode.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Address: Serializable {

    @SerializedName("street")
    @Expose
    lateinit var street: String

    @SerializedName("suite")
    @Expose
    lateinit var suite: String

    @SerializedName("city")
    @Expose
    lateinit var city: String

    @SerializedName("zipcode")
    @Expose
    lateinit var zipcode: String

    @SerializedName("geo")
    @Expose
    lateinit var geo: Geo

}
