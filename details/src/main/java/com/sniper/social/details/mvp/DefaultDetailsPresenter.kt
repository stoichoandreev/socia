package com.sniper.social.details.mvp

import com.sniper.social.base.mvp.PresenterView
import io.reactivex.disposables.CompositeDisposable

class DefaultDetailsPresenter(private val disposable: CompositeDisposable): DetailsPresenter {

    private var view: DetailsPresenter.View? = null

    override fun method() {

    }

    override fun attachView(view: PresenterView) {
        this.view = view as DetailsPresenter.View
    }

    override fun destroy() {
        this.view = null
        disposable.dispose()
    }
}