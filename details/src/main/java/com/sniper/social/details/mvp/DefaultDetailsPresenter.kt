package com.sniper.social.details.mvp

import com.sniper.social.base.mvp.PresenterView
import com.sniper.social.converter.posts.PostViewModel
import com.sniper.social.details.usecases.GetPostDetailsUseCase
import io.reactivex.disposables.CompositeDisposable

class DefaultDetailsPresenter(private val getPostDetails: GetPostDetailsUseCase,
                              private val disposable: CompositeDisposable) : DetailsPresenter {

    private var view: DetailsPresenter.View? = null

    override fun fetchPostDetails(post: PostViewModel?) {
        disposable.add(getPostDetails.getDetailsInformation(post)
                .doOnSubscribe { view?.showLoading(true) }
                .doOnTerminate { view?.showLoading(false) }
                .subscribe({ details ->
                    view?.showDetails(details)
                }, { error ->
                    view?.showError(error.localizedMessage)
                }))
    }

    override fun attachView(view: PresenterView) {
        this.view = view as DetailsPresenter.View
    }

    override fun destroy() {
        this.view = null
        disposable.dispose()
    }
}
