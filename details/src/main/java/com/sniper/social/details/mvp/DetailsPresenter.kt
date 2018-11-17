package com.sniper.social.details.mvp

import com.sniper.social.base.mvp.Presenter
import com.sniper.social.base.mvp.PresenterView

interface DetailsPresenter : Presenter {

    fun method()

    interface View: PresenterView {

    }
}