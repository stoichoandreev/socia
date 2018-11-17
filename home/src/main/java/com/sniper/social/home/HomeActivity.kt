package com.sniper.social.home

import android.os.Bundle
import com.sniper.social.base.di.Scope
import com.sniper.social.base.ui.BaseActivity
import com.sniper.social.converter.posts.PostViewModel
import com.sniper.social.home.di.homeModule
import com.sniper.social.home.mvp.HomePresenter
import org.koin.android.ext.android.inject
import org.koin.dsl.module.Module

class HomeActivity : BaseActivity<HomePresenter>(), HomePresenter.View {

    override val presenter: HomePresenter by inject()

    override fun getScreenLayout(): Int = R.layout.activity_home

    override fun getModule(): Module = homeModule

    override fun getScreenScope(): String = Scope.HOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.attachView(this)
        presenter.fetchPosts()
    }

    override fun showPosts(postsList: List<PostViewModel>) {
        //Todo need implementation
    }

    override fun showError(errorMessage: String) {
        displayError(errorMessage)
    }

    override fun showLoading(show: Boolean) {

    }
}
