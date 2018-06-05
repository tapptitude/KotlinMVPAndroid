package com.tapptitude.mvpsample.di.builders

import com.tapptitude.mvpsample.presentation.home.activities.HomeActivity
import com.tapptitude.mvpsample.di.modules.AppModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(AppModule::class)])
    abstract fun bindMainActivity(): HomeActivity
}