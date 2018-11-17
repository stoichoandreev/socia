package com.sniper.social.details.services

import com.sniper.social.api.typicode.apis.UsersApi
import com.sniper.social.base.utils.filterItem
import com.sniper.social.converter.users.UserViewModel
import com.sniper.social.converter.users.UsersConverter
import io.reactivex.Observable

class DefaultUsersService(private val api: UsersApi,
                          private val converter: UsersConverter) : UsersService {

    override fun getUsers(): Observable<List<UserViewModel>> =
            api.users().map { response -> converter.map(response) }

    override fun getUser(userId: Int?): Observable<UserViewModel> =
            api.users().map { response -> converter.map(response).filterItem { it.id == userId } }

}
