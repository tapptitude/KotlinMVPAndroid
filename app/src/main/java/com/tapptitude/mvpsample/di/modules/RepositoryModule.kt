package com.tapptitude.mvpsample.di.modules

import com.tapptitude.mvpsample.data.network.DateTimeApi
import com.tapptitude.mvpsample.data.network.IpApi
import com.tapptitude.mvpsample.data.persistence.datetime.DateTimeNetworkRepository
import com.tapptitude.mvpsample.data.persistence.datetime.DateTimeRepository
import com.tapptitude.mvpsample.data.persistence.ip.IpNetworkRepository
import com.tapptitude.mvpsample.data.persistence.ip.IpRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    internal fun provideDateTimeRepository(dateTimeApi: DateTimeApi): DateTimeRepository {
        return DateTimeNetworkRepository(dateTimeApi)
    }

    @Provides
    @Singleton
    internal fun provideIpRepository(ipApi: IpApi): IpRepository {
        return IpNetworkRepository(ipApi)
    }
}
