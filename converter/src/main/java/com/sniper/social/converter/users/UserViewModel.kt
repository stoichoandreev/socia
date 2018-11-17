package com.sniper.social.converter.users

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserViewModel(val id: Int, val name: String): Parcelable

class PostViewModelBuilder {
    var id = -1
    var name = ""

    fun build(): UserViewModel = UserViewModel(id, name)
}

inline fun createUserViewModel(init: PostViewModelBuilder.() -> Unit): UserViewModel {
    val builder = PostViewModelBuilder()
    builder.init()
    return builder.build()
}
