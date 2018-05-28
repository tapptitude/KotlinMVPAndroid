package com.tapptitude.mvpsample.ui.home.view

import com.tapptitude.mvpsample.data.network.models.DateTime
import com.tapptitude.mvpsample.ui.common.BaseView

interface SampleView: BaseView {

    fun onDateTimeLoaded(dateTime: DateTime)
}