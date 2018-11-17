package com.sniper.social.home.services

import com.sniper.social.api.typicode.apis.PostsApi
import com.sniper.social.converter.posts.PostViewModel
import com.sniper.social.converter.posts.PostsConverter
import io.reactivex.Observable
import io.reactivex.Scheduler

class DefaultPostsService(private val api: PostsApi,
                          private val converter: PostsConverter,
                          private val notifications: Scheduler,
                          private val worker: Scheduler): PostsService {

    override fun getPosts(): Observable<List<PostViewModel>> =
            api.posts()
                    .map { response -> converter.map(response) }
                    .subscribeOn(worker)
                    .observeOn(notifications)
}
