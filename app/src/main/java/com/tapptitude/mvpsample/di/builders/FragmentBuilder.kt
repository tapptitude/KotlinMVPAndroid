package com.tapptitude.mvpsample.di.builders

import com.tapptitude.mvpsample.di.modules.AppModule
import com.tapptitude.mvpsample.presentation.home.fragments.SampleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = [(AppModule::class)])
    abstract fun bindQuestionFragment(): SampleFragment
}