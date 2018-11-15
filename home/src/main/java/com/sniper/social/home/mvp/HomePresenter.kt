package com.sniper.social.home.mvp

import com.sniper.social.base.mvp.Presenter
import com.sniper.social.base.mvp.PresenterView

interface HomePresenter<T : PresenterView> : Presenter<T> {

    fun method()

    interface View: PresenterView {

    }
}