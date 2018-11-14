package com.sniper.social.api.typicode

import okhttp3.Interceptor
import okhttp3.Response

class TypiCodeClientInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder().build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
