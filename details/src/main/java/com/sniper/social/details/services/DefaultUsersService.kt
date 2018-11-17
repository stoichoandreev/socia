package com.sniper.social.details.services

import com.sniper.social.api.typicode.apis.UsersApi
import com.sniper.social.converter.users.UserViewModel
import com.sniper.social.converter.users.UsersConverter
import io.reactivex.Observable
import io.reactivex.Scheduler

class DefaultUsersService(private val api: UsersApi,
                          private val converter: UsersConverter,
                          private val notifications: Scheduler,
                          private val worker: Scheduler) : UsersService {

    override fun getUsers(): Observable<List<UserViewModel>> =
            api.users()
                    .map { response -> converter.map(response) }
                    .subscribeOn(worker)
                    .observeOn(notifications)


}
