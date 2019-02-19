package com.tapptitude.mvpsample.di.builders

import com.tapptitude.mvpsample.presentation.home.activities.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): HomeActivity
}