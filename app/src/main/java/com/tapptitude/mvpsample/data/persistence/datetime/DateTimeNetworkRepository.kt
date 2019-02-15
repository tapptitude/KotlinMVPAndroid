package com.tapptitude.mvpsample.data.persistence.datetime

import com.tapptitude.mvpsample.data.network.DateTimeApi
import com.tapptitude.mvpsample.data.network.models.DateTime
import com.tapptitude.mvpsample.providers.SchedulerProvider
import io.reactivex.Observable

class DateTimeNetworkRepository(
        private val dateTimeApi: DateTimeApi,
        private val schedulerProvider: SchedulerProvider
) : DateTimeRepository {

    override fun loadDateTime(): Observable<DateTime> {
        return dateTimeApi.getDateTime()
                .subscribeOn(schedulerProvider.io())
    }
}