package com.sniper.social.api.okhttp

class DefaultOkHttpConfig : OKHttpConfig {

    override val connectTimeout: Long
        get() = CONNECTION_TIMEOUT

    override val readTimeout: Long
        get() = READ_TIMEOUT

    override val writeTimeout: Long
        get() = WRITE_TIMEOUT

    companion object {

        const val CONNECTION_TIMEOUT: Long = 30
        const val READ_TIMEOUT: Long = 30
        const val WRITE_TIMEOUT: Long = 30
    }
}
