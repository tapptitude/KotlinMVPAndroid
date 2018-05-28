package com.tapptitude.mvpsample.utils

import android.app.Activity
import android.app.Application
import com.tapptitude.mvpsample.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MvpSampleApp : Application(), HasActivityInjector {

    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        setUpDependencyInjection()
    }

    private fun setUpDependencyInjection() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }
}
