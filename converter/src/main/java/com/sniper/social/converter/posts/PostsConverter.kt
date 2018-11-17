package com.sniper.social.converter.posts

import com.sniper.social.api.typicode.models.Post
import com.sniper.social.converter.BaseConverter

class PostsConverter : BaseConverter<List<Post>, List<PostViewModel>> {

    override fun map(response: List<Post>): List<PostViewModel> =
            response.map {
                createPostViewModel {
                    id = it.id!!
                    title = it.postTitle
                    body = it.postBody
                }
            }
}
