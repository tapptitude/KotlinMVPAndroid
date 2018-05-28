package com.tapptitude.mvpsample.ui.home.presenter

import com.tapptitude.mvpsample.data.network.models.IpAddress
import com.tapptitude.mvpsample.data.persistence.ip.IpRepository
import com.tapptitude.mvpsample.ui.common.BasePresenter
import com.tapptitude.mvpsample.ui.home.view.HomeView
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class HomePresenter @Inject constructor(private val repository: IpRepository) : BasePresenter<HomeView>() {

    fun loadIpAddress() {
        repository.loadIpAddress()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SelfDisposingObserver<IpAddress>() {
                    override fun onNext(ipAddress: IpAddress) {
                        view?.onIpAddressLoaded(ipAddress.ip)
                    }

                    override fun onError(e: Throwable) {
                        e.message?.let { view?.onIpAddressLoadFailure(it) }
                    }
                })
    }
}
