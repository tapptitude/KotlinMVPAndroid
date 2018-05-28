package com.tapptitude.mvpsample.ui.common.fragments

import android.content.Context
import android.support.v4.app.Fragment
import com.tapptitude.mvpsample.ui.common.BasePresenter
import com.tapptitude.mvpsample.ui.common.BaseView
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<V : BaseView> : Fragment(), BaseView {

    override fun onAttach(context: Context?) {
        performInjection()
        super.onAttach(context)
        attachPresenter()
    }

    @Suppress("UNCHECKED_CAST")
    private fun attachPresenter() {
        getPresenter().bind(this as V)
    }

    private fun performInjection() {
        AndroidSupportInjection.inject(this)
    }

    protected abstract fun getPresenter(): BasePresenter<V>
}
