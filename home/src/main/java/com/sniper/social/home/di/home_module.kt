package com.sniper.social.home.di

import com.sniper.social.api.RetrofitClient
import com.sniper.social.api.typicode.apis.PostsApi
import com.sniper.social.base.di.Dependency
import com.sniper.social.base.di.Scope
import com.sniper.social.converter.posts.PostsConverter
import com.sniper.social.home.mvp.DefaultHomePresenter
import com.sniper.social.home.mvp.HomePresenter
import com.sniper.social.home.services.DefaultPostsService
import com.sniper.social.home.services.PostsService
import com.sniper.social.home.usecases.GetPostsUseCase
import org.koin.dsl.module.module

val homeModule = module {

    scope(Scope.HOME, override = true) {
        val client = get(Dependency.TYPI_CODE_API_RETROFIT_CLIENT) as RetrofitClient
        client.api(PostsApi::class.java)
    }

    scope(Scope.HOME, override = true) {
        PostsConverter()
    }

    scope(Scope.HOME, override = true) {
        DefaultPostsService(get(), get(), get(Dependency.MAIN_THREAD), get(Dependency.BACKGROUND_THREAD)) as PostsService
    }

    scope(Scope.HOME, override = true) {
        GetPostsUseCase(get())
    }

    scope(Scope.HOME, override = true) {
        DefaultHomePresenter(get(), get()) as HomePresenter
    }

}
