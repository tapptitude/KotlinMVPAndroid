package com.tapptitude.mvpsample.presentation.home.presenter

import com.tapptitude.mvpsample.data.network.models.IpAddress
import com.tapptitude.mvpsample.data.persistence.ip.IpRepository
import com.tapptitude.mvpsample.preferance.UserSessionManager
import com.tapptitude.mvpsample.presentation.common.BasePresenter
import com.tapptitude.mvpsample.presentation.home.view.HomeView
import com.tapptitude.mvpsample.providers.SchedulerProvider
import javax.inject.Inject

class HomePresenter @Inject constructor(
        private val repository: IpRepository,
        private val schedulerProvider: SchedulerProvider,
        private val userSessionManager: UserSessionManager
) : BasePresenter<HomeView>() {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    fun loadIpAddress() {
        repository.loadIpAddress()
                .observeOn(schedulerProvider.mainThread())
                .subscribe(object : SelfDisposingObserver<IpAddress>() {
                    override fun onNext(ipAddress: IpAddress) {
                        val ip = ipAddress.ip

                        view?.onIpAddressLoaded(ip)
                        userSessionManager.saveIp(ip)
                    }

                    override fun onError(e: Throwable) {
                        e.message?.let { view?.onIpAddressLoadFailure(it) }
                    }
                })
    }
}
