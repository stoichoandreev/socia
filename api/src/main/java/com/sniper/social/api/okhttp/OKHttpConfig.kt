package com.sniper.social.api.okhttp

interface OKHttpConfig {

    val connectTimeout: Long

    val readTimeout: Long

    val writeTimeout: Long
}
