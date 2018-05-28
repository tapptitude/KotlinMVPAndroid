package com.tapptitude.mvpsample.data.persistence.datetime

import com.tapptitude.mvpsample.data.network.models.DateTime
import io.reactivex.Observable

interface DateTimeRepository {

    fun loadDateTime(): Observable<DateTime>
}