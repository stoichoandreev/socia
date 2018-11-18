package com.sniper.social.details.di

import com.sniper.social.api.RetrofitClient
import com.sniper.social.api.typicode.apis.CommentsApi
import com.sniper.social.api.typicode.apis.UsersApi
import com.sniper.social.base.di.Dependency
import com.sniper.social.base.di.Scope
import com.sniper.social.converter.comments.CommentsConverter
import com.sniper.social.converter.users.UsersConverter
import com.sniper.social.details.mvp.DefaultDetailsPresenter
import com.sniper.social.details.mvp.DetailsPresenter
import com.sniper.social.details.services.CommentsService
import com.sniper.social.details.services.DefaultCommentsService
import com.sniper.social.details.services.DefaultUsersService
import com.sniper.social.details.services.UsersService
import com.sniper.social.details.usecases.GetPostDetailsUseCase
import org.koin.dsl.module.module

val detailsModule = module {

    scope(Scope.DETAILS, override = true) {
        val client = get(Dependency.TYPI_CODE_API_RETROFIT_CLIENT) as RetrofitClient
        client.api(UsersApi::class.java)
    }

    scope(Scope.DETAILS, override = true) {
        val client = get(Dependency.TYPI_CODE_API_RETROFIT_CLIENT) as RetrofitClient
        client.api(CommentsApi::class.java)
    }

    scope(Scope.DETAILS, override = true) {
        UsersConverter()
    }

    scope(Scope.DETAILS, override = true) {
        CommentsConverter()
    }

    scope(Scope.DETAILS, override = true) {
        DefaultUsersService(get(), get()) as UsersService
    }

    scope(Scope.DETAILS, override = true) {
        DefaultCommentsService(get(), get()) as CommentsService
    }

    scope(Scope.DETAILS, override = true) {
        GetPostDetailsUseCase(get(), get(), get(Dependency.MAIN_THREAD), get(Dependency.BACKGROUND_THREAD))
    }

    scope(Scope.DETAILS, override = true) {
        DefaultDetailsPresenter(get(), get()) as DetailsPresenter
    }

}
