package com.sniper.social.api.typicode.apis

import com.sniper.social.api.EndPoints
import com.sniper.social.api.typicode.models.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface PostsApi {

    @GET(EndPoints.POSTS)
    fun posts(): Observable<List<Post>>

}
