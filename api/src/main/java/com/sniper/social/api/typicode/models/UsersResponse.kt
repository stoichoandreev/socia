package com.sniper.social.api.typicode.models

import java.io.Serializable

open class UsersResponse: Serializable {

    lateinit var result: MutableList<User>

}
