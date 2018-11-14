package com.sniper.social.api

sealed class Environment {

    object TypiCodeAPI: Environment() {
        override val productionUrl: String
            get() = "http://jsonplaceholder.typicode.com/"

        override val testUrl: String
            get() = "http://jsonplaceholder.typicode.com/"
    }

    fun getBuildUrl(isDebug: Boolean): String =
            when {
                isDebug -> testUrl
                else -> productionUrl
            }

    abstract val productionUrl: String
    abstract val testUrl: String
}
