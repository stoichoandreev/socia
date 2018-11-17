package com.sniper.social.base.mvp

interface Presenter {

    fun attachView(view: PresenterView)

    fun destroy()

}
