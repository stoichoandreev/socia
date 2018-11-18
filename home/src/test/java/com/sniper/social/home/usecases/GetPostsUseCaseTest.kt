package com.sniper.social.home.usecases

import com.sniper.social.converter.posts.PostViewModel
import com.sniper.social.converter.posts.createPostViewModel
import com.sniper.social.home.services.PostsService
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import org.mockito.Mockito.`when` as whenMock

@RunWith(MockitoJUnitRunner::class)
class GetPostsUseCaseTest {

    @Mock
    private lateinit var mockPostApiService: PostsService

    private val notificationsThread = Schedulers.trampoline()

    private val workerThread = Schedulers.trampoline()

    private lateinit var tested: GetPostsUseCase

    @Before
    fun setUp() {
        tested = GetPostsUseCase(mockPostApiService, notificationsThread, workerThread)
    }

    @Test
    fun `test GetPostsUseCase gets all posts from PostsService`() {
        //given
        whenMock(mockPostApiService.getPosts()).thenReturn(Observable.just(listOf()))
        //when
        tested.getAllPosts()
        //test
        verify(mockPostApiService).getPosts()
    }

    @Test
    fun `test GetPostsUseCase gets all posts from PostsService and delivers them with the expected view data type`() {
        //given
        val convertedPostsListData = listOf(
                createPostViewModel {
                    id = 1
                    userId = 10
                    title = "Post1"
                    body = "Post1 body"
                },
                createPostViewModel {
                    id = 2
                    userId = 10
                    title = "Post2"
                    body = "Post2 body"
                },
                createPostViewModel {
                    id = 3
                    userId = 20
                    title = "Post3"
                    body = "Post3 body"
                }
        )
        whenMock(mockPostApiService.getPosts()).thenReturn(Observable.just(convertedPostsListData))
        val testObserver = TestObserver<List<PostViewModel>>()
        //when
        val observable = tested.getAllPosts()
        //test
        observable.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val listResult = testObserver.values()[0]
        assertEquals(3, listResult.size)

        assertEquals(10, listResult[0].userId)
        assertEquals(1, listResult[0].id)
        assertEquals("Post1", listResult[0].title)
        assertEquals("Post1 body", listResult[0].body)

        assertEquals(10, listResult[1].userId)
        assertEquals(2, listResult[1].id)
        assertEquals("Post2", listResult[1].title)
        assertEquals("Post2 body", listResult[1].body)

        assertEquals(20, listResult[2].userId)
        assertEquals(3, listResult[2].id)
        assertEquals("Post3", listResult[2].title)
        assertEquals("Post3 body", listResult[2].body)
    }

    @Test
    fun `test GetPostsUseCase throws exception if the services fails`() {
        //given
        whenMock(mockPostApiService.getPosts()).thenReturn(Observable.error(Throwable("")))
        val testObserver = TestObserver<List<PostViewModel>>()
        //when
        val observable = tested.getAllPosts()
        //test
        observable.subscribe(testObserver)
        testObserver.assertError(Throwable::class.java)
    }
}
