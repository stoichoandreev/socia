package com.sniper.social.details.ui

import android.os.Bundle
import android.view.View
import com.sniper.social.base.di.Scope
import com.sniper.social.base.intents.IntentExtra
import com.sniper.social.base.ui.BaseActivity
import com.sniper.social.converter.posts.PostViewModel
import com.sniper.social.details.R
import com.sniper.social.details.di.detailsModule
import com.sniper.social.details.mvp.DetailsPresenter
import kotlinx.android.synthetic.main.activity_details.*
import org.koin.android.ext.android.inject
import org.koin.dsl.module.Module

class DetailsActivity : BaseActivity<DetailsPresenter>(), DetailsPresenter.View {

    override val presenter: DetailsPresenter by inject()

    override val screenLayout: Int = R.layout.activity_details

    override val screenModule: Module = detailsModule

    override val screenScope: String = Scope.DETAILS

    private var postData: PostViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        obtainExtras(intent.extras)

        initToolbar()
        presenter.attachView(this)
        presenter.method()
    }

    override fun showError(errorMessage: String) {
        displayError(errorMessage)
    }

    override fun showLoading(show: Boolean) {
        progress_indicator.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun obtainExtras(extras: Bundle?) {
        extras?.let {
            postData = it.getParcelable(IntentExtra.POST_EXTRA)
        }
    }

    private fun initToolbar() {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = getString(R.string.details_title)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

}
