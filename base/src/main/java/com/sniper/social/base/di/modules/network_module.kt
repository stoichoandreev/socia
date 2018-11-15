package com.sniper.social.base.di.modules

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.sniper.social.api.ApiConfiguration
import com.sniper.social.api.RetrofitClient
import com.sniper.social.api.okhttp.DefaultOkHttpConfig
import com.sniper.social.api.okhttp.OKHttpConfig
import com.sniper.social.api.typicode.TypiCodeApiConfiguration
import com.sniper.social.api.typicode.TypiCodeClient
import com.sniper.social.api.typicode.TypiCodeClientInterceptor
import com.sniper.social.base.BuildConfig
import com.sniper.social.base.di.Dependency
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat
import java.util.concurrent.TimeUnit

val networkModule = module {

    single(Dependency.TYPI_CODE_API_OK_HTTP_CONFIG) {
        DefaultOkHttpConfig() as OKHttpConfig
    }

    single(Dependency.HTTP_BODY_LOGGING) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) as HttpLoggingInterceptor
    }

    single(Dependency.TYPI_CODE_API_CLIENT_INTERCEPTOR) {
        TypiCodeClientInterceptor() as Interceptor
    }

    single(Dependency.DEFAULT_GSON_SETUP) {
        GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setDateFormat(DateFormat.LONG)
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create()
    }

    single(Dependency.GSON_CONVERTER_FACTORY) {
        GsonConverterFactory.create(get(Dependency.DEFAULT_GSON_SETUP)) as Converter.Factory
    }

    single(Dependency.RX_CALL_ADAPTER_FACTORY) {
        RxJava2CallAdapterFactory.create() as CallAdapter.Factory
    }

    single(Dependency.TYPI_CODE_API_CONFIGURATION) {
        TypiCodeApiConfiguration(BuildConfig.DEBUG) as ApiConfiguration
    }

    single(Dependency.TYPI_CODE_API_OK_HTTP_CLIENT) {
        val config = get<OKHttpConfig>(Dependency.TYPI_CODE_API_OK_HTTP_CONFIG)
        val bodyLogging = get<HttpLoggingInterceptor>(Dependency.HTTP_BODY_LOGGING)
        val apiInterceptor = get<Interceptor>(Dependency.TYPI_CODE_API_CLIENT_INTERCEPTOR)
        val builder = OkHttpClient.Builder()
                .connectTimeout(config.connectTimeout, TimeUnit.SECONDS)
                .readTimeout(config.readTimeout, TimeUnit.SECONDS)
                .writeTimeout(config.writeTimeout, TimeUnit.SECONDS)
                .addInterceptor(apiInterceptor)
        //we can add Stetho as well
        if (BuildConfig.DEBUG) {
            builder.interceptors().add(bodyLogging)
        }
        builder.build()
    }

    single(Dependency.TYPI_CODE_API_RETROFIT_CLIENT) {
        TypiCodeClient(get(Dependency.GSON_CONVERTER_FACTORY),
                get(Dependency.RX_CALL_ADAPTER_FACTORY),
                get(Dependency.TYPI_CODE_API_OK_HTTP_CLIENT),
                get(Dependency.TYPI_CODE_API_CONFIGURATION)) as RetrofitClient
    }

    single(Dependency.BACKGROUND_THREAD) {
        Schedulers.io()
    }

    single(Dependency.MAIN_THREAD) {
        AndroidSchedulers.mainThread();
    }

}
