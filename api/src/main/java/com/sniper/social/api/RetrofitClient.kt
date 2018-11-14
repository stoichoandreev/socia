package com.sniper.social.api

interface RetrofitClient {
    fun <T> api(service: Class<T>): T
}
