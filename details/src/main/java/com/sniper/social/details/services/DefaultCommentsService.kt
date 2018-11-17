package com.sniper.social.details.services

import com.sniper.social.api.typicode.apis.CommentsApi
import com.sniper.social.converter.comments.CommentViewModel
import com.sniper.social.converter.comments.CommentsConverter
import io.reactivex.Observable

class DefaultCommentsService(private val api: CommentsApi,
                             private val converter: CommentsConverter) : CommentsService {

    override fun getComments(): Observable<List<CommentViewModel>> =
            api.comments().map { response -> converter.map(response) }

    override fun getPostComments(postId: Int?): Observable<List<CommentViewModel>> =
            api.comments().map { response -> converter.map(response).filter { it.postId == postId } }

}
