package com.sniper.social.converter.posts

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostViewModel(val id: Int, val userId: Int, val title: String, val body: String) : Parcelable

class PostViewModelBuilder {
    var id = -1
    var userId = -1
    var title = ""
    var body = ""

    fun build(): PostViewModel = PostViewModel(id, userId, title, body)
}

inline fun createPostViewModel(init: PostViewModelBuilder.() -> Unit): PostViewModel {
    val builder = PostViewModelBuilder()
    builder.init()
    return builder.build()
}
