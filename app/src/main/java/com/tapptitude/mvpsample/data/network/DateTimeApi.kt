package com.tapptitude.mvpsample.data.network

import com.tapptitude.mvpsample.data.network.models.DateTime
import io.reactivex.Observable
import retrofit2.http.GET

interface DateTimeApi {

    @GET(".")
    fun getDateTime(): Observable<DateTime>
}