package com.sniper.social.home.usecases

import com.sniper.social.converter.posts.PostViewModel
import com.sniper.social.home.services.PostsService
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetPostsUseCase(private val postsService: PostsService,
                      private val notifications: Scheduler,
                      private val worker: Scheduler) {

    fun getAllPosts(): Observable<List<PostViewModel>> =
            postsService.getPosts()
                    .subscribeOn(worker)
                    .observeOn(notifications)
}
