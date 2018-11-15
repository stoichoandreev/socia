package com.sniper.social.details.mvp

import com.sniper.social.base.mvp.Presenter
import com.sniper.social.base.mvp.PresenterView

interface DetailsPresenter<T : PresenterView> : Presenter<T> {

    fun method()

    interface View: PresenterView {

    }
}