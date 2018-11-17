package com.sniper.social.converter.users

data class UserViewModel(val id: Int, val name: String)

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
