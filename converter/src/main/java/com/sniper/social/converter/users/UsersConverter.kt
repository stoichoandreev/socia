package com.sniper.social.converter.users

import com.sniper.social.api.typicode.models.User
import com.sniper.social.converter.BaseConverter

class UsersConverter : BaseConverter<List<User>, List<UserViewModel>> {

    override fun map(response: List<User>): List<UserViewModel> =
            response.map {
                createUserViewModel {
                    id = it.id!!
                    name = it.name
                }
            }
}
