package com.sniper.social.details.services

import com.sniper.social.converter.comments.CommentViewModel
import io.reactivex.Observable

interface CommentsService {

    fun getComments(): Observable<List<CommentViewModel>>

}
