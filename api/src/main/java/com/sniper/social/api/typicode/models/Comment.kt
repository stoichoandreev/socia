package com.sniper.social.api.typicode.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Comment: Serializable {

    @SerializedName("postId")
    @Expose
    var postId: Int? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    lateinit var name: String

    @SerializedName("email")
    @Expose
    lateinit var email: String

    @SerializedName("body")
    @Expose
    lateinit var body: String

}
