package com.sniper.social.api.typicode.models

import java.io.Serializable

open class PostsResponse: Serializable {

    lateinit var result: MutableList<Post>

}
