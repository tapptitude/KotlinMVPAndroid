package com.tapptitude.mvpsample.di.components

import android.app.Application
import com.tapptitude.mvpsample.di.builders.ActivityBuilder
import com.tapptitude.mvpsample.di.builders.FragmentBuilder
import com.tapptitude.mvpsample.di.modules.AppModule
import com.tapptitude.mvpsample.di.modules.NetworkModule
import com.tapptitude.mvpsample.di.modules.RepositoryModule
import com.tapptitude.mvpsample.utils.MvpSampleApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (NetworkModule::class),
    (RepositoryModule::class), (ActivityBuilder::class), (FragmentBuilder::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: MvpSampleApp)
}
