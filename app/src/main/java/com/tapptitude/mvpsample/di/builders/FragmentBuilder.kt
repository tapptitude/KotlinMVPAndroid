package com.tapptitude.mvpsample.di.builders

import com.tapptitude.mvpsample.presentation.home.fragments.SampleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun bindQuestionFragment(): SampleFragment
}