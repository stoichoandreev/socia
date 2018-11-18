package com.sniper.social.details.services

import com.sniper.social.api.typicode.apis.CommentsApi
import com.sniper.social.api.typicode.models.Comment
import com.sniper.social.converter.comments.CommentViewModel
import com.sniper.social.converter.comments.CommentsConverter
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals
import org.mockito.Mockito.`when` as whenMock

class DefaultCommentsServiceTest {

    @Mock
    private lateinit var mockCommentsApi: CommentsApi

    @Mock
    private lateinit var mockCommentsConverter: CommentsConverter

    private val realCommentsConverter: CommentsConverter = CommentsConverter()

    private lateinit var tested: DefaultCommentsService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `test DefaultCommentsService fetches all comments from CommentsApi`() {
        //given
        tested = DefaultCommentsService(mockCommentsApi, mockCommentsConverter)
        val serverCommentsListData = listOf(Comment(), Comment())
        whenMock(mockCommentsApi.comments()).thenReturn(Observable.just(serverCommentsListData))
        //when
        tested.getComments()
        //test
        Mockito.verify(mockCommentsApi).comments()
        Mockito.verifyNoMoreInteractions(mockCommentsApi)
    }

    @Test
    fun `test DefaultCommentsService fetches all comments from CommentsApi and converts them to correct view model`() {
        //given
        tested = DefaultCommentsService(mockCommentsApi, mockCommentsConverter)
        val serverCommentsListData = listOf(Comment(), Comment())
        whenMock(mockCommentsApi.comments()).thenReturn(Observable.just(serverCommentsListData))
        //when
        tested.getComments().test()
        //test
        Mockito.verify(mockCommentsConverter).map(serverCommentsListData)
    }

    @Test
    fun `test DefaultCommentsService fetches all comments from CommentsApi, filters specific comments by postId  and converts them to correct view model`() {
        //given
        tested = DefaultCommentsService(mockCommentsApi, realCommentsConverter)
        val somePostId = 10
        val comment1 = Comment()
        val comment2 = Comment()
        val comment3 = Comment()
        val comment4 = Comment()
        with(comment1) {
            postId = somePostId
            body = "body 1"
        }
        with(comment2) {
            postId = 20
            body = ""
        }
        with(comment3) {
            postId = 20
            body = ""
        }
        with(comment4) {
            postId = somePostId
            body = "body 2"
        }
        val serverCommentsListData = listOf(comment1, comment2, comment3, comment4)
        val testObserver = TestObserver<List<CommentViewModel>>()
        whenMock(mockCommentsApi.comments()).thenReturn(Observable.just(serverCommentsListData))
        //when
        val result = tested.getPostComments(somePostId)
        //test
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val listResult = testObserver.values()[0]
        assertEquals(2, listResult.size)
        assertEquals(somePostId, listResult[0].postId)
        assertEquals( "body 1", listResult[0].body)
        assertEquals(somePostId, listResult[1].postId)
        assertEquals("body 2", listResult[1].body)
    }
}
