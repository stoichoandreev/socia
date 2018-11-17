package com.sniper.social.details

import android.os.Bundle
import com.sniper.social.base.di.Scope
import com.sniper.social.base.ui.BaseActivity
import com.sniper.social.details.di.detailsModule
import com.sniper.social.details.mvp.DetailsPresenter
import org.koin.android.ext.android.inject
import org.koin.dsl.module.Module

class DetailsActivity : BaseActivity<DetailsPresenter>(), DetailsPresenter.View {

    override val presenter: DetailsPresenter by inject()

    override fun getScreenLayout(): Int = R.layout.activity_details

    override fun getModule(): Module = detailsModule

    override fun getScreenScope(): String = Scope.DETAILS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.attachView(this)
        presenter.method()
    }

    override fun showError(errorMessage: String) {
        displayError(errorMessage)
    }

    override fun showLoading(show: Boolean) {
        //Todo need implementation here
    }

}
