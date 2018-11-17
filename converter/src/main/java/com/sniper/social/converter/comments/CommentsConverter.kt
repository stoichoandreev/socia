package com.sniper.social.converter.comments

import com.sniper.social.api.typicode.models.Comment
import com.sniper.social.converter.BaseConverter

class CommentsConverter : BaseConverter<List<Comment>, List<CommentViewModel>> {

    override fun map(response: List<Comment>): List<CommentViewModel> =
            response.map {
                createCommentViewModel {
                    postId = it.postId!!
                    body = it.body
                }
            }
}
