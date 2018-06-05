package com.tapptitude.mvpsample.presentation.common.fragments

import android.content.Context
import android.support.v4.app.Fragment
import com.tapptitude.mvpsample.presentation.common.BasePresenter
import com.tapptitude.mvpsample.presentation.common.BaseView
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<V : BaseView> : Fragment(), BaseView {

    override fun onAttach(context: Context?) {
        performInjection()
        super.onAttach(context)
        attachPresenter()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter().unbind()
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
