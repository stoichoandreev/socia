package com.sniper.social.details.usecases

import com.sniper.social.converter.comments.CommentViewModel
import com.sniper.social.converter.details.DetailsViewModel
import com.sniper.social.converter.details.createDetailsViewModel
import com.sniper.social.converter.posts.PostViewModel
import com.sniper.social.converter.users.UserViewModel
import com.sniper.social.details.services.CommentsService
import com.sniper.social.details.services.UsersService
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.functions.BiFunction
import java.lang.IllegalArgumentException

class GetPostDetailsUseCase(private val usersService: UsersService,
                            private val commentsService: CommentsService,
                            private val notifications: Scheduler,
                            private val worker: Scheduler) {

    fun getDetailsInformation(postData: PostViewModel?): Observable<DetailsViewModel> =
            Observable.zip(
                    usersService.getUser(postData?.userId),
                    commentsService.getPostComments(postData?.id),
                    BiFunction<UserViewModel, List<CommentViewModel>, DetailsViewModel> { userData, commentsList ->
                        when (postData) {
                            null -> throw IllegalArgumentException("Missing data")
                            else -> createDetailsViewModel {
                                postTitle = postData.title
                                postBody = postData.body
                                userName = userData.name
                                commentsCount = commentsList.size
                            }
                        }
                    })
                    .subscribeOn(worker)
                    .observeOn(notifications)
}
