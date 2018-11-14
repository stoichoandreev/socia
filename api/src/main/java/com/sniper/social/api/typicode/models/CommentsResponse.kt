package com.sniper.social.api.typicode.models

import java.io.Serializable

open class CommentsResponse: Serializable {

    lateinit var result: MutableList<Comment>

}
