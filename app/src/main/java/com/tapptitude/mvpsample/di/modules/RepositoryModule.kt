package com.tapptitude.mvpsample.di.modules

import com.tapptitude.mvpsample.data.network.DateTimeApi
import com.tapptitude.mvpsample.data.network.IpApi
import com.tapptitude.mvpsample.data.persistence.datetime.DateTimeNetworkRepository
import com.tapptitude.mvpsample.data.persistence.datetime.DateTimeRepository
import com.tapptitude.mvpsample.data.persistence.ip.IpNetworkRepository
import com.tapptitude.mvpsample.data.persistence.ip.IpRepository
import com.tapptitude.mvpsample.providers.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    internal fun provideDateTimeRepository(
            dateTimeApi: DateTimeApi,
            schedulerProvider: SchedulerProvider
    ): DateTimeRepository {
        return DateTimeNetworkRepository(dateTimeApi, schedulerProvider)
    }

    @Provides
    @Singleton
    internal fun provideIpRepository(
            ipApi: IpApi,
            schedulerProvider: SchedulerProvider
    ): IpRepository {
        return IpNetworkRepository(ipApi, schedulerProvider)
    }
}
