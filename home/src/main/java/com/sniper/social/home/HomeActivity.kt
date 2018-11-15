package com.sniper.social.home

import android.os.Bundle
import com.sniper.social.base.di.Scope
import com.sniper.social.base.ui.BaseActivity
import com.sniper.social.home.di.homeModule
import com.sniper.social.home.mvp.HomePresenter
import org.koin.android.ext.android.inject
import org.koin.dsl.module.Module

class HomeActivity : BaseActivity() {

    private val presenter : HomePresenter<HomePresenter.View> by inject()

    override fun getScreenLayout(): Int = R.layout.activity_home

    override fun getModule(): Module = homeModule

    override fun getScreenScope(): String = Scope.HOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.method()
    }
}
