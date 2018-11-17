package com.sniper.social.converter.posts

import com.sniper.social.api.typicode.models.Post
import org.junit.Assert.assertEquals
import org.junit.Test

class PostsConverterTest {

    private val tested = PostsConverter()

    @Test
    fun `test PostsConverter converts posts api response to post view model list`() {
        //given
        val post1 = Post()
        val post2 = Post()
        with(post1) {
            id = 1
            userId = 12
            postTitle = "Post1 title"
            postBody = "Some body of post 1"
        }
        with(post2) {
            id = 2
            userId = 22
            postTitle = "Post2 title"
            postBody = "Some body of post 2"
        }
        val usersList = listOf(post1, post2)
        //when
        val result = tested.map(usersList)
        //test
        assertEquals(2, result.size)

        assertEquals(1, result[0].id)
        assertEquals(12, result[0].userId)
        assertEquals("Some body of post 1", result[0].body)

        assertEquals(2, result[1].id)
        assertEquals(22, result[1].userId)
        assertEquals("Some body of post 2", result[1].body)
    }

    @Test
    fun `test PostsConverter converts empty posts api response to empty post view model list`() {
        //given
        val postsList = listOf<Post>()
        //when
        val result = tested.map(postsList)
        //test
        assertEquals(0, result.size)
    }
}