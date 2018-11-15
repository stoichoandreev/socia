package com.sniper.social.home.di

import com.sniper.social.base.di.Scope
import com.sniper.social.home.mvp.DefaultHomePresenter
import com.sniper.social.home.mvp.HomePresenter
import org.koin.dsl.module.module

val homeModule = module {

    scope(Scope.HOME) {
        DefaultHomePresenter() as HomePresenter<HomePresenter.View>
    }

}
