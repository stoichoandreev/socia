package com.sniper.social.details.services

import com.sniper.social.api.typicode.apis.UsersApi
import com.sniper.social.api.typicode.models.User
import com.sniper.social.converter.users.UserViewModel
import com.sniper.social.converter.users.UsersConverter
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals
import org.mockito.Mockito.`when` as whenMock

class DefaultUsersServiceTest {

    @Mock
    private lateinit var mockUsersApi: UsersApi

    @Mock
    private lateinit var mockUsersConverter: UsersConverter

    private val realUsersConverter: UsersConverter = UsersConverter()

    private lateinit var tested: DefaultUsersService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `test DefaultUsersService fetches all users from UsersApi`() {
        //given
        tested = DefaultUsersService(mockUsersApi, mockUsersConverter)
        val serverUsersListData = listOf(User(), User())
        whenMock(mockUsersApi.users()).thenReturn(Observable.just(serverUsersListData))
        //when
        tested.getUsers()
        //test
        Mockito.verify(mockUsersApi).users()
        Mockito.verifyNoMoreInteractions(mockUsersApi)
    }

    @Test
    fun `test DefaultUsersService fetches all users from UsersApi and converts them to correct view model`() {
        //given
        tested = DefaultUsersService(mockUsersApi, mockUsersConverter)
        val serverUsersListData = listOf(User(), User())
        whenMock(mockUsersApi.users()).thenReturn(Observable.just(serverUsersListData))
        //when
        tested.getUsers().test()
        //test
        Mockito.verify(mockUsersConverter).map(serverUsersListData)
    }

    @Test
    fun `test DefaultUsersService fetches all users from UsersApi, filters specific user by userId  and converts it to correct view model`() {
        //given
        tested = DefaultUsersService(mockUsersApi, realUsersConverter)
        val someUserId = 10
        val user1 = User()
        val user2 = User()
        val user3 = User()
        with(user1) {
            id = someUserId
            name = "UserName"
        }
        with(user2) {
            id = 20
            name = ""
        }
        with(user3) {
            id = 30
            name = ""
        }
        val serverUsersListData = listOf(user1, user2, user3)
        val testObserver = TestObserver<UserViewModel>()
        whenMock(mockUsersApi.users()).thenReturn(Observable.just(serverUsersListData))
        //when
        val result = tested.getUser(someUserId)
        //test
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val userResult = testObserver.values()[0]
        assertEquals(someUserId, userResult.id)
        assertEquals( "UserName", userResult.name)
    }
}