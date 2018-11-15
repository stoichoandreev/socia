package com.sniper.social.base.mvp

interface Presenter<in V : PresenterView> {

    fun attachView(view: V)

    fun detachView()

    fun destroy()

}