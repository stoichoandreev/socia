package com.sniper.social.home.mvp

import com.sniper.social.base.mvp.PresenterView
import com.sniper.social.home.usecases.GetPostsUseCase
import io.reactivex.disposables.CompositeDisposable

class DefaultHomePresenter(private val getPosts: GetPostsUseCase,
                           private val disposable: CompositeDisposable) : HomePresenter {

    private var view: HomePresenter.View? = null

    override fun fetchPosts() {
        disposable.add(getPosts.getAllPosts()
                .doOnSubscribe { view?.showLoading(true) }
                .doOnComplete { view?.showLoading(false) }
                .doOnTerminate { view?.showLoading(false) }
                .subscribe({ posts ->
            view?.showPosts(posts)
        }, { error ->
            view?.showError(error.localizedMessage)
        }))
    }

    override fun attachView(view: PresenterView) {
        this.view = view as HomePresenter.View
    }

    override fun destroy() {
        this.view = null
        disposable.dispose()
    }
}
