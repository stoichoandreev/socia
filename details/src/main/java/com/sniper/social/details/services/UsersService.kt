package com.sniper.social.details.services

import com.sniper.social.converter.users.UserViewModel
import io.reactivex.Observable

interface UsersService {

    fun getUsers(): Observable<List<UserViewModel>>

    fun getUser(userId:Int?): Observable<UserViewModel>

}
