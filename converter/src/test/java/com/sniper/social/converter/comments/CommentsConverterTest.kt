package com.sniper.social.converter.comments

import com.sniper.social.api.typicode.models.Comment
import org.junit.Test

import org.junit.Assert.*

class CommentsConverterTest {

    private val tested = CommentsConverter()

    @Test
    fun `test CommentsConverter converts comments api response to comment view model list`() {
        //given
        val comment1 = Comment()
        val comment2 = Comment()
        with(comment1) {
            id = 1
            postId = 12
            email = "email@em.com"
            name = "Name"
            body = "Some body of comment 1"
        }
        with(comment2) {
            id = 2
            postId = 22
            email = "mail@em.com"
            name = "SecondName"
            body = "Some body of comment 2"
        }
        val commentsList = listOf(comment1, comment2)
        //when
        val result = tested.map(commentsList)
        //test
        assertEquals(2, result.size)

        assertEquals(12, result[0].postId)
        assertEquals("Some body of comment 1", result[0].body)

        assertEquals(22, result[1].postId)
        assertEquals("Some body of comment 2", result[1].body)
    }

    @Test
    fun `test CommentsConverter converts empty comments api response to empty comment view model list`() {
        //given
        val commentsList = listOf<Comment>()
        //when
        val result = tested.map(commentsList)
        //test
        assertEquals(0, result.size)
    }
}
