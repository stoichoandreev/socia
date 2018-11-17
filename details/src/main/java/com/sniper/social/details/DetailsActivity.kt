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

    override val screenLayout: Int = R.layout.activity_details

    override val screenModule: Module = detailsModule

    override val screenScope: String = Scope.DETAILS

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
