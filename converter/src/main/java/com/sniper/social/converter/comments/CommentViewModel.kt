package com.sniper.social.converter.comments

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommentViewModel(val postId: Int, val body: String): Parcelable

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
