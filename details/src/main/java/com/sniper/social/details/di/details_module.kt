package com.sniper.social.details.di

import com.sniper.social.api.RetrofitClient
import com.sniper.social.api.typicode.apis.CommentsApi
import com.sniper.social.api.typicode.apis.PostsApi
import com.sniper.social.api.typicode.apis.UsersApi
import com.sniper.social.base.di.Dependency
import com.sniper.social.base.di.Scope
import com.sniper.social.converter.comments.CommentsConverter
import com.sniper.social.converter.posts.PostsConverter
import com.sniper.social.converter.users.UsersConverter
import com.sniper.social.details.mvp.DefaultDetailsPresenter
import com.sniper.social.details.mvp.DetailsPresenter
import io.reactivex.disposables.CompositeDisposable
import org.koin.dsl.module.module

val detailsModule = module {

    scope(Scope.DETAILS, override = true) {
        CompositeDisposable()
    }

    scope(Scope.DETAILS) {
        val client = get(Dependency.TYPI_CODE_API_RETROFIT_CLIENT) as RetrofitClient
        client.api(UsersApi::class.java)
    }

    scope(Scope.DETAILS) {
        val client = get(Dependency.TYPI_CODE_API_RETROFIT_CLIENT) as RetrofitClient
        client.api(CommentsApi::class.java)
    }

    scope(Scope.DETAILS, override = true) {
        UsersConverter()
    }

    scope(Scope.DETAILS, override = true) {
        CommentsConverter()
    }

    scope(Scope.DETAILS) {
        DefaultDetailsPresenter(get()) as DetailsPresenter
    }

}
