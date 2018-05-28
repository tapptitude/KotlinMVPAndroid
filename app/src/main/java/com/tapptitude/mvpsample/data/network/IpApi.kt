package com.tapptitude.mvpsample.data.network

import com.tapptitude.mvpsample.data.network.models.IpAddress
import io.reactivex.Observable
import retrofit2.http.GET

interface IpApi {

    @GET(".")
    fun getIpAddress(): Observable<IpAddress>
}