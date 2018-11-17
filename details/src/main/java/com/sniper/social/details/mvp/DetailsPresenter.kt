package com.sniper.social.details.mvp

import com.sniper.social.base.mvp.Presenter
import com.sniper.social.base.mvp.PresenterView
import com.sniper.social.converter.details.DetailsViewModel
import com.sniper.social.converter.posts.PostViewModel

interface DetailsPresenter : Presenter {

    fun fetchPostDetails(post: PostViewModel?)

    interface View: PresenterView {

        fun showDetails(details: DetailsViewModel)

    }
}
