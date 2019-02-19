package com.tapptitude.mvpsample

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.tapptitude.mvpsample.data.network.DateTimeApi
import com.tapptitude.mvpsample.data.network.models.DateTime
import com.tapptitude.mvpsample.data.persistence.datetime.DateTimeNetworkRepository
import com.tapptitude.mvpsample.presentation.home.presenter.SamplePresenter
import com.tapptitude.mvpsample.presentation.home.view.SampleView
import com.tapptitude.mvpsample.providers.SchedulerProvider
import com.tapptitude.mvpsample.util.TestSchedulerProvider
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

class SamplePresenterTest {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var dateTimeApi: DateTimeApi

    @Mock
    lateinit var sampleView: SampleView

    private lateinit var testDateTime: DateTime

    private lateinit var networkRepository: DateTimeNetworkRepository

    private lateinit var samplePresenter: SamplePresenter

    private lateinit var schedulerProvider: SchedulerProvider

    @Before
    fun setup() {
        testDateTime = DateTime("Time", "Date", 123123123)
        whenever(dateTimeApi.getDateTime()).thenReturn(Observable.just(testDateTime))

        schedulerProvider = TestSchedulerProvider()

        networkRepository = DateTimeNetworkRepository(dateTimeApi, schedulerProvider)

        samplePresenter = SamplePresenter(networkRepository, schedulerProvider)
        samplePresenter.bind(sampleView)
    }

    @Test
    fun testRepository() {
        val testObservable = networkRepository.loadDateTime()
                .test()

        testObservable.assertValue(testDateTime)
    }

    @Test
    fun testPresenter() {
        samplePresenter.loadDateTime()

        verify(sampleView, times(1)).onDateTimeLoaded(testDateTime)
    }

    @Test
    fun testFail() {
        whenever(dateTimeApi.getDateTime()).thenReturn(Observable.error(RuntimeException("my_exception")))
        samplePresenter.loadDateTime()

        verify(sampleView, times(1)).onDatetimeLoadingFailed("my_exception")
    }
}