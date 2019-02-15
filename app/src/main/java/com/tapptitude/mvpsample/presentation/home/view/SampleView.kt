package com.tapptitude.mvpsample.presentation.home.view

import com.tapptitude.mvpsample.data.network.models.DateTime
import com.tapptitude.mvpsample.presentation.common.BaseView

interface SampleView : BaseView {

    fun onDateTimeLoaded(dateTime: DateTime)

    fun onDatetimeLoadingFailed(error: String?)
}