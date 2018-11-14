package com.sniper.social.api.typicode

import com.sniper.social.api.ApiConfiguration
import com.sniper.social.api.Environment

class TypiCodeApiConfiguration(private val isDebug: Boolean): ApiConfiguration {

    override val baseURL: String
        get() = Environment.TypiCodeAPI.getBuildUrl(isDebug)

}
