package com.sniper.social.base.mvp

interface PresenterView {

    fun showError(errorMessage: String)

    fun showLoading(show: Boolean)

}
