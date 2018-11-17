package com.sniper.social.details.services

import com.sniper.social.api.typicode.apis.CommentsApi
import com.sniper.social.converter.comments.CommentViewModel
import com.sniper.social.converter.comments.CommentsConverter
import io.reactivex.Observable
import io.reactivex.Scheduler

class DefaultCommentsService(private val api: CommentsApi,
                             private val converter: CommentsConverter,
                             private val notifications: Scheduler,
                             private val worker: Scheduler) : CommentsService {

    override fun getComments(): Observable<List<CommentViewModel>> =
            api.comments()
                    .map { response -> converter.map(response) }
                    .subscribeOn(worker)
                    .observeOn(notifications)


}
