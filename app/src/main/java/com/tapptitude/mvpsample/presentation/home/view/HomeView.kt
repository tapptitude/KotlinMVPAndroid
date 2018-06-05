package com.tapptitude.mvpsample.presentation.home.view

import com.tapptitude.mvpsample.presentation.common.BaseView

interface HomeView : BaseView {

    fun onIpAddressLoaded(ipAddress: String)

    fun onIpAddressLoadFailure(message: String)
}