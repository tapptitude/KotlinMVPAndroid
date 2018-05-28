package com.tapptitude.mvpsample.ui.home.presenter

import com.tapptitude.mvpsample.data.persistence.datetime.DateTimeNetworkRepository
import com.tapptitude.mvpsample.ui.common.BasePresenter
import com.tapptitude.mvpsample.ui.home.view.SampleView
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class SamplePresenter @Inject constructor(private val repository: DateTimeNetworkRepository) :
        BasePresenter<SampleView>() {

    fun loadDateTime() {
        repository.loadDateTime()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(SelfDisposingObserver(onNextListener = { view?.onDateTimeLoaded(it) }))
    }
}