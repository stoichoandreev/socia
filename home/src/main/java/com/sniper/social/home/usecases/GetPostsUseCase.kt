package com.sniper.social.home.usecases

import com.sniper.social.converter.posts.PostViewModel
import com.sniper.social.home.services.PostsService
import io.reactivex.Observable

class GetPostsUseCase(private val postsService: PostsService) {

    fun getAllPosts(): Observable<List<PostViewModel>> {
        return postsService.getPosts()
    }
}