package com.tapptitude.mvpsample.presentation.home.presenter

import com.tapptitude.mvpsample.data.persistence.datetime.DateTimeRepository
import com.tapptitude.mvpsample.presentation.common.BasePresenter
import com.tapptitude.mvpsample.presentation.home.view.SampleView
import com.tapptitude.mvpsample.providers.SchedulerProvider
import javax.inject.Inject

class SamplePresenter @Inject constructor(
        private val repository: DateTimeRepository,
        private val schedulerProvider: SchedulerProvider
) : BasePresenter<SampleView>() {

    fun loadDateTime() {
        repository.loadDateTime()
                .observeOn(schedulerProvider.mainThread())
                .subscribe(SelfDisposingObserver(onSuccessAction = { view?.onDateTimeLoaded(it) }))
    }
}