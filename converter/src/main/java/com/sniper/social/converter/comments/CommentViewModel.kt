package com.sniper.social.converter.comments

data class CommentViewModel(val postId: Int, val body: String)

class CommentViewModelBuilder {
    var postId = -1
    var body = ""

    fun build(): CommentViewModel = CommentViewModel(postId, body)
}

inline fun createCommentViewModel(init: CommentViewModelBuilder.() -> Unit): CommentViewModel {
    val builder = CommentViewModelBuilder()
    builder.init()
    return builder.build()
}
