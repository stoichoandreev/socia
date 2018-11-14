package com.sniper.social.api.typicode.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class User: Serializable {

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    lateinit var name: String

    @SerializedName("username")
    @Expose
    lateinit var userName: String

    @SerializedName("email")
    @Expose
    lateinit var email: String

    @SerializedName("address")
    @Expose
    lateinit var address: Address

}
