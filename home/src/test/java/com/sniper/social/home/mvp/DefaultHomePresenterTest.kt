package com.sniper.social.home.mvp

import com.sniper.social.converter.posts.createPostViewModel
import com.sniper.social.home.usecases.GetPostsUseCase
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
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
class DefaultHomePresenterTest{

    @Mock
    private lateinit var mockView: HomePresenter.View

    @Mock
    private lateinit var mockGetPostsUseCase: GetPostsUseCase

    @Mock
    private lateinit var mockCompositeDisposable: CompositeDisposable

    private lateinit var tested: DefaultHomePresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        tested = DefaultHomePresenter(mockGetPostsUseCase, mockCompositeDisposable)
    }

    @Test
    fun `test DefaultHomePresenter fetches posts list with success, but does not call the view if it is not attached`() {
        //given
        val postsListData = listOf(createPostViewModel {  })
        whenMock(mockGetPostsUseCase.getAllPosts()).thenReturn(Observable.just(postsListData))
        //when
        tested.fetchPosts()
        //test
        Mockito.verifyNoMoreInteractions(mockView)
    }

    @Test
    fun `test DefaultHomePresenter fetches posts list with success`() {
        //given
        val postsListData = listOf(createPostViewModel {  })
        whenMock(mockGetPostsUseCase.getAllPosts()).thenReturn(Observable.just(postsListData))
        //when
        tested.attachView(mockView)
        tested.fetchPosts()
        //test
        verify(mockView).showLoading(true)
        verify(mockView).showPosts(postsListData)
        verify(mockView).showLoading(false)
    }

    @Test
    fun `test DefaultHomePresenter fetches posts list with error`() {
        //given
        val error =  "error message"
        whenMock(mockGetPostsUseCase.getAllPosts()).thenReturn(Observable.error(Throwable(error)))
        //when
        tested.attachView(mockView)
        tested.fetchPosts()
        //test
        verify(mockView).showLoading(true)
        verify(mockView).showError(error)
        verify(mockView).showLoading(false)
    }

    @Test
    fun `test DefaultHomePresenter destroys the subscriptions`() {
        //when
        tested.destroy()
        //test
        verify(mockCompositeDisposable).dispose()
    }
}
