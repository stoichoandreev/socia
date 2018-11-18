package com.sniper.social.details.usecases

import com.sniper.social.converter.comments.createCommentViewModel
import com.sniper.social.converter.details.DetailsViewModel
import com.sniper.social.converter.posts.createPostViewModel
import com.sniper.social.converter.users.createUserViewModel
import com.sniper.social.details.services.CommentsService
import com.sniper.social.details.services.UsersService
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import org.mockito.Mockito.`when` as whenMock

@RunWith(MockitoJUnitRunner::class)
class GetPostDetailsUseCaseTest {

    @Mock
    private lateinit var mockUsersService: UsersService

    @Mock
    private lateinit var mockCommentsService: CommentsService

    private val notificationsThread = Schedulers.trampoline()

    private val workerThread = Schedulers.trampoline()

    private lateinit var tested: GetPostDetailsUseCase

    @Before
    fun setUp() {
        tested = GetPostDetailsUseCase(mockUsersService, mockCommentsService, notificationsThread, workerThread)
    }

    @Test
    fun `test GetPostDetailsUseCase gets all users and comments from UsersService and CommentsService`() {
        //given
        val postData = createPostViewModel {
            id = 10
            userId = 1
            title = "title"
            body = "body body body"
        }
        whenMock(mockUsersService.getUser(postData.userId)).thenReturn(Observable.just(createUserViewModel { }))
        whenMock(mockCommentsService.getPostComments(postData.id)).thenReturn(Observable.just(listOf(createCommentViewModel { })))
        //when
        tested.getDetailsInformation(postData)
        //test
        Mockito.verify(mockUsersService).getUser(postData.userId)
        Mockito.verify(mockCommentsService).getPostComments(postData.id)
    }

    @Test
    fun `test GetPostDetailsUseCase gets post comments and post user data and delivers the expected details view data`() {
        //given
        val expectedUserId = 1
        val expectedPostId = 10
        val expectedUserName = "User1"
        val expectedPostTitle = "title"
        val expectedPostBody = "body body body"
        val postData = createPostViewModel {
            id = expectedPostId
            userId = expectedUserId
            title = expectedPostTitle
            body = expectedPostBody
        }
        val convertedCommentsListData = listOf(
                createCommentViewModel {
                    postId = expectedPostId
                    body = "Comment body1"
                },
                createCommentViewModel {
                    postId = expectedPostId
                    body = "Comment body3"
                }
        )
        whenMock(mockUsersService.getUser(postData.userId)).thenReturn(Observable.just(createUserViewModel {
            id = expectedUserId
            name = expectedUserName
        }))
        whenMock(mockCommentsService.getPostComments(postData.id)).thenReturn(Observable.just(convertedCommentsListData))
        val testObserver = TestObserver<DetailsViewModel>()
        //when
        val observable = tested.getDetailsInformation(postData)
        //test
        observable.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val result = testObserver.values()[0]

        assertEquals(expectedPostTitle, result.postTitle)
        assertEquals(expectedPostBody, result.postBody)
        assertEquals(expectedUserName, result.userName)
        assertEquals(2, result.commentsCount)
    }

    @Test
    fun `test GetPostDetailsUseCase throws IllegalArgumentException if post data is not provided`() {
        //given
        val postData = null
        whenMock(mockUsersService.getUser(null)).thenReturn(Observable.just(createUserViewModel { }))
        whenMock(mockCommentsService.getPostComments(null)).thenReturn(Observable.just(listOf(createCommentViewModel { })))
        val testObserver = TestObserver<DetailsViewModel>()
        //when
        val observable = tested.getDetailsInformation(postData)
        //test
        observable.subscribe(testObserver)
        testObserver.assertErrorMessage("Missing data")
        testObserver.assertError(IllegalArgumentException::class.java)
    }

    @Test
    fun `test GetPostDetailsUseCase throws exception if some of the services fails`() {
        //given
        val postData = createPostViewModel {
            id = 1
            userId = 10
            title = ""
            body = ""
        }
        whenMock(mockUsersService.getUser(postData.userId)).thenReturn(Observable.just(createUserViewModel { }))
        whenMock(mockCommentsService.getPostComments(postData.id)).thenReturn(Observable.error(Throwable("")))
        val testObserver = TestObserver<DetailsViewModel>()
        //when
        val observable = tested.getDetailsInformation(postData)
        //test
        observable.subscribe(testObserver)
        testObserver.assertError(Throwable::class.java)
    }

}
