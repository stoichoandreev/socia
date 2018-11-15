package com.sniper.social.details.di

import com.sniper.social.base.di.Scope
import com.sniper.social.details.mvp.DefaultDetailsPresenter
import com.sniper.social.details.mvp.DetailsPresenter
import org.koin.dsl.module.module

val detailsModule = module {

    scope(Scope.HOME) {
        DefaultDetailsPresenter() as DetailsPresenter<DetailsPresenter.View>
    }

}
