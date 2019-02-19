package com.tapptitude.mvpsample.di.modules

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.tapptitude.mvpsample.preferance.UserSessionManager
import com.tapptitude.mvpsample.providers.RuntimeSchedulerProvider
import com.tapptitude.mvpsample.providers.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val USER_PREFS = "user_sh_preferences"

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideAppGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideUserSessionManager(
            context: Context,
            gson: Gson
    ): UserSessionManager {
        val sharedPrefs = context.applicationContext.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE)
        return UserSessionManager(gson, sharedPrefs)
    }

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider {
        return RuntimeSchedulerProvider()
    }
}
