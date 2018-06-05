package com.tapptitude.mvpsample.presentation.home.presenter

import com.tapptitude.mvpsample.data.persistence.datetime.DateTimeRepository
import com.tapptitude.mvpsample.presentation.common.BasePresenter
import com.tapptitude.mvpsample.presentation.home.view.SampleView
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class SamplePresenter @Inject constructor(private val repository: DateTimeRepository) :
        BasePresenter<SampleView>() {

    fun loadDateTime() {
        repository.loadDateTime()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(SelfDisposingObserver(onSuccessAction = { view?.onDateTimeLoaded(it) }))
    }
}