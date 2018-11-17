package com.sniper.social.api.typicode.apis

import com.sniper.social.api.EndPoints
import com.sniper.social.api.typicode.models.Comment
import io.reactivex.Observable
import retrofit2.http.GET

interface CommentsApi {

    @GET(EndPoints.COMMENTS)
    fun comments(): Observable<List<Comment>>

}
