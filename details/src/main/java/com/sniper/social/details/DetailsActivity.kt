package com.sniper.social.details

import com.sniper.social.base.di.Scope
import com.sniper.social.base.ui.BaseActivity
import com.sniper.social.details.di.detailsModule
import org.koin.dsl.module.Module

class DetailsActivity : BaseActivity() {

    override fun getScreenLayout(): Int = R.layout.activity_details

    override fun getModule(): Module = detailsModule

    override fun getScreenScope(): String = Scope.DETAILS
}
