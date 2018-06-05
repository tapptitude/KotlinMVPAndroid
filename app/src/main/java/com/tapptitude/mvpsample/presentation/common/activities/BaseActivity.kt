package com.tapptitude.mvpsample.presentation.common.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.tapptitude.mvpsample.presentation.common.BasePresenter
import com.tapptitude.mvpsample.presentation.common.BaseView
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity<V : BaseView> : AppCompatActivity(), HasSupportFragmentInjector, BaseView {

    @Inject
    internal lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performInjection()
        attachPresenter()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter().unbind()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return childFragmentInjector
    }

    @Suppress("UNCHECKED_CAST")
    private fun attachPresenter() {
        getPresenter().bind(this as V)
    }

    private fun performInjection() {
        AndroidInjection.inject(this)
    }

    protected abstract fun getPresenter(): BasePresenter<V>
}