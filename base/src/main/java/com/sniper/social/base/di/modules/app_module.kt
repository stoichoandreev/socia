package com.sniper.social.base.di.modules

import com.sniper.social.base.BuildConfig
import com.sniper.social.base.services.AppLinksService
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val socialAppModule = module {

    single { androidContext() }

    single { AppLinksService(BuildConfig.APP_LINK_BASE_URL) }

    factory { CompositeDisposable() }

}