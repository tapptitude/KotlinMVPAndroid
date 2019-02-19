package com.tapptitude.mvpsample.data.persistence.ip

import com.tapptitude.mvpsample.data.network.IpApi
import com.tapptitude.mvpsample.data.network.models.IpAddress
import com.tapptitude.mvpsample.providers.SchedulerProvider
import io.reactivex.Observable

class IpNetworkRepository(
        private val ipApi: IpApi,
        private val schedulerProvider: SchedulerProvider
) : IpRepository {

    override fun loadIpAddress(): Observable<IpAddress> {
        return ipApi.getIpAddress()
                .subscribeOn(schedulerProvider.io())
    }
}