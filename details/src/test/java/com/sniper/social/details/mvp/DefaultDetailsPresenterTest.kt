package com.sniper.social.details.mvp

import com.sniper.social.converter.details.createDetailsViewModel
import com.sniper.social.converter.posts.createPostViewModel
import com.sniper.social.details.usecases.GetPostDetailsUseCase
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
class DefaultDetailsPresenterTest {

    @Mock
    private lateinit var mockView: DetailsPresenter.View

    @Mock
    private lateinit var mockGetPostDetailsUseCase: GetPostDetailsUseCase

    @Mock
    private lateinit var mockCompositeDisposable: CompositeDisposable

    private lateinit var tested: DefaultDetailsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        tested = DefaultDetailsPresenter(mockGetPostDetailsUseCase, mockCompositeDisposable)
    }

    @Test
    fun `test DefaultDetailsPresenter fetches post details with success, but does not call the view if it is not attached`() {
        //given
        val postData = createPostViewModel { }
        val detailsData = createDetailsViewModel { }
        whenMock(mockGetPostDetailsUseCase.getDetailsInformation(postData)).thenReturn(Observable.just(detailsData))
        //when
        tested.fetchPostDetails(postData)
        //test
        Mockito.verifyNoMoreInteractions(mockView)
    }

    @Test
    fun `test DefaultDetailsPresenter fetches post details with success`() {
        //given
        val postData = createPostViewModel { }
        val detailsData = createDetailsViewModel { }
        whenMock(mockGetPostDetailsUseCase.getDetailsInformation(postData)).thenReturn(Observable.just(detailsData))
        //when
        tested.attachView(mockView)
        tested.fetchPostDetails(postData)
        //test
        verify(mockView).showLoading(true)
        verify(mockView).showDetails(detailsData)
        verify(mockView).showLoading(false)
    }

    @Test
    fun `test DefaultDetailsPresenter fetches post details with error`() {
        //given
        val postData = createPostViewModel { }
        val error = "error message"
        whenMock(mockGetPostDetailsUseCase.getDetailsInformation(postData)).thenReturn(Observable.error(Throwable(error)))
        //when
        tested.attachView(mockView)
        tested.fetchPostDetails(postData)
        //test
        verify(mockView).showLoading(true)
        verify(mockView).showError(error)
        verify(mockView).showLoading(false)
    }

    @Test
    fun `test DefaultDetailsPresenter destroys the subscriptions`() {
        //when
        tested.destroy()
        //test
        verify(mockCompositeDisposable).dispose()
    }

}
