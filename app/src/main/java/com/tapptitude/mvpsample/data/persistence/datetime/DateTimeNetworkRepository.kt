package com.tapptitude.mvpsample.data.persistence.datetime

import com.tapptitude.mvpsample.data.network.DateTimeApi
import com.tapptitude.mvpsample.data.network.models.DateTime
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class DateTimeNetworkRepository(private val dateTimeApi: DateTimeApi) : DateTimeRepository {

    override fun loadDateTime(): Observable<DateTime> {
        return dateTimeApi.getDateTime()
                .subscribeOn(Schedulers.io())
    }
}