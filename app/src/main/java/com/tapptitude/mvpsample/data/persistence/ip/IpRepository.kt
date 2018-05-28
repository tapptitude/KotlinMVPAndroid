package com.tapptitude.mvpsample.data.persistence.ip

import com.tapptitude.mvpsample.data.network.models.IpAddress
import io.reactivex.Observable

interface IpRepository {

    fun loadIpAddress(): Observable<IpAddress>
}