package com.sniper.social.converter.posts

data class PostViewModel(val id: Int, val title: String, val body: String)

class PostViewModelBuilder {
    var id = -1
    var title = ""
    var body = ""

    fun build(): PostViewModel = PostViewModel(id, title, body)
}

inline fun createPostViewModel(init: PostViewModelBuilder.() -> Unit): PostViewModel {
    val builder = PostViewModelBuilder()
    builder.init()
    return builder.build()
}
