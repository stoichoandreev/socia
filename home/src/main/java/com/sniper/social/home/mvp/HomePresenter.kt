package com.sniper.social.home.mvp

import com.sniper.social.base.mvp.Presenter
import com.sniper.social.base.mvp.PresenterView
import com.sniper.social.converter.posts.PostViewModel

interface HomePresenter : Presenter {

    fun fetchPosts()

    interface View: PresenterView {
        fun showPosts(postsList: List<PostViewModel>)
    }
}
