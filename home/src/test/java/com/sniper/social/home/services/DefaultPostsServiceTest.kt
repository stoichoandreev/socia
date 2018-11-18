package com.sniper.social.home.services

import com.sniper.social.api.typicode.apis.PostsApi
import com.sniper.social.api.typicode.models.Post
import com.sniper.social.converter.posts.PostsConverter
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenMock

@RunWith(MockitoJUnitRunner::class)
class DefaultPostsServiceTest {

    @Mock
    private lateinit var mockPostsApi: PostsApi

    @Mock
    private lateinit var mockPostsConverter: PostsConverter

    private lateinit var tested: DefaultPostsService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        tested = DefaultPostsService(mockPostsApi, mockPostsConverter)
    }

    @Test
    fun `test DefaultPostsService fetches all posts from PostsApi`() {
        //given
        val serverPostsListData = listOf(Post(), Post())
        whenMock(mockPostsApi.posts()).thenReturn(Observable.just(serverPostsListData))
        //when
        tested.getPosts()
        //test
        verify(mockPostsApi).posts()
        Mockito.verifyNoMoreInteractions(mockPostsApi)
    }

    @Test
    fun `test DefaultPostsService fetches all posts from PostsApi and converts them to correct view model`() {
        //given
        val serverPostsListData = listOf(Post(), Post())
        whenMock(mockPostsApi.posts()).thenReturn(Observable.just(serverPostsListData))
        //when
        tested.getPosts().test()
        //test
        verify(mockPostsConverter).map(serverPostsListData)
    }
}