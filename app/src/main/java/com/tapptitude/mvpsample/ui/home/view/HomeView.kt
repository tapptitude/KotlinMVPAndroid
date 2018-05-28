package com.tapptitude.mvpsample.ui.home.view

import com.tapptitude.mvpsample.ui.common.BaseView

interface HomeView : BaseView {

    fun onIpAddressLoaded(ipAddress: String)

    fun onIpAddressLoadFailure(message: String)
}