package com.sniper.social.api.typicode.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Post: Serializable {

    @SerializedName("userId")
    @Expose
    var userId: Int? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("title")
    @Expose
    lateinit var postTitle: String

    @SerializedName("body")
    @Expose
    lateinit var postBody: String

}
