package com.sniper.social.base

import android.app.Application
import com.sniper.social.base.di.modules.networkModule
import com.sniper.social.base.di.modules.socialAppModule
import org.koin.android.ext.android.startKoin

class SocialApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(socialAppModule, networkModule))
    }
}
