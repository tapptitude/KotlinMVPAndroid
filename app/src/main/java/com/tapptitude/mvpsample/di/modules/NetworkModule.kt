package com.tapptitude.mvpsample.di.modules

import android.app.Application
import com.google.gson.Gson
import com.tapptitude.mvpsample.BuildConfig
import com.tapptitude.mvpsample.data.network.DateTimeApi
import com.tapptitude.mvpsample.data.network.IpApi
import com.tapptitude.mvpsample.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

private const val CACHE_MAX_SIZE = (10 * 1024 * 1024).toLong()

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideDateTimeApi(retrofitBuilder: Retrofit.Builder): DateTimeApi {
        return retrofitBuilder
                .baseUrl(getDateTimeEndpoint())
                .build()
                .create(DateTimeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideIpApi(retrofitBuilder: Retrofit.Builder): IpApi {
        return retrofitBuilder
                .baseUrl(getIpEndpoint())
                .build()
                .create(IpApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    @Provides
    @Singleton
    internal fun provideHttpClient(application: Application): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpBuilder.addInterceptor(interceptor)
        }

        val cacheDir = File(application.cacheDir.absolutePath, application.packageName)
        okHttpBuilder.cache(okhttp3.Cache(cacheDir, CACHE_MAX_SIZE))
        return okHttpBuilder.build()
    }

    private fun getDateTimeEndpoint(): String {
        return if (BuildConfig.DEBUG) {
            Constants.Api.Development.DATETIME_ENDPOINT
        } else {
            Constants.Api.Production.DATETIME_ENDPOINT
        }
    }

    private fun getIpEndpoint(): String {
        return if (BuildConfig.DEBUG) {
            Constants.Api.Development.IP_ENDPOINT
        } else {
            Constants.Api.Production.IP_ENDPOINT
        }
    }
}
