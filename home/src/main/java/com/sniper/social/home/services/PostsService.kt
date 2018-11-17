package com.sniper.social.home.services

import com.sniper.social.converter.posts.PostViewModel
import io.reactivex.Observable

interface PostsService {

    fun getPosts(): Observable<List<PostViewModel>>

}

