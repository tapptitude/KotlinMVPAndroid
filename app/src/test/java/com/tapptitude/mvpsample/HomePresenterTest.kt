package com.tapptitude.mvpsample

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.tapptitude.mvpsample.data.network.IpApi
import com.tapptitude.mvpsample.data.network.models.IpAddress
import com.tapptitude.mvpsample.data.persistence.ip.IpNetworkRepository
import com.tapptitude.mvpsample.data.persistence.ip.IpRepository
import com.tapptitude.mvpsample.preferance.UserSessionManager
import com.tapptitude.mvpsample.presentation.home.presenter.HomePresenter
import com.tapptitude.mvpsample.presentation.home.view.HomeView
import com.tapptitude.mvpsample.providers.SchedulerProvider
import com.tapptitude.mvpsample.util.TestSchedulerProvider
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

class HomePresenterTest {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var ipApi: IpApi

    @Mock
    lateinit var homeView: HomeView

    @Mock
    lateinit var userSessionManager: UserSessionManager

    private lateinit var networkRepository: IpRepository

    private lateinit var testIp: IpAddress

    private lateinit var homePresenter: HomePresenter

    private lateinit var schedulerProvider: SchedulerProvider

    private val testIpValue = "my_test_ip_here"

    @Before
    fun setup() {
        testIp = IpAddress(testIpValue)

        whenever(ipApi.getIpAddress()).thenReturn(Observable.just(testIp))

        schedulerProvider = TestSchedulerProvider()

        networkRepository = IpNetworkRepository(ipApi, schedulerProvider)

        homePresenter = HomePresenter(networkRepository, schedulerProvider, userSessionManager)
        homePresenter.bind(homeView)
    }

    @Test
    fun testRepository() {
        val testObservable = networkRepository.loadIpAddress()
                .test()

        testObservable.assertValue(testIp)
    }

    @Test
    fun testIpSaving() {
        homePresenter.loadIpAddress()

        verify(userSessionManager, times(1)).saveIp(testIpValue)
    }

    @Test
    fun testFail() {
        whenever(ipApi.getIpAddress()).thenReturn(Observable.error(RuntimeException("my_exception")))
        homePresenter.loadIpAddress()

        verify(homeView, times(1)).onIpAddressLoadFailure("my_exception")
    }
}