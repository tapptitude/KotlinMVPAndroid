package com.tapptitude.mvpsample.data.persistence.ip

import com.tapptitude.mvpsample.data.network.IpApi
import com.tapptitude.mvpsample.data.network.models.IpAddress
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class IpNetworkRepository(private val ipApi: IpApi) : IpRepository {

    fun loadIpAddress(): Observable<IpAddress> {
        return ipApi.getIpAddress()
                .subscribeOn(Schedulers.io())
    }
}