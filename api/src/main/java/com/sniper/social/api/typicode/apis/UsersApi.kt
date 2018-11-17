package com.sniper.social.api.typicode.apis

import com.sniper.social.api.EndPoints
import com.sniper.social.api.typicode.models.User
import io.reactivex.Observable
import retrofit2.http.GET

interface UsersApi {

    @GET(EndPoints.USERS)
    fun users(): Observable<List<User>>

}
