package it.carusopi.stargazers.base.di

import dagger.Module
import dagger.Provides
import it.carusopi.stargazers.api.StaragazersApiClient
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by carusopi on 27/10/2017.
 */
@Module
class ApiModule {
    @Provides
    @Singleton
    fun providesEndpoints(retrofit: Retrofit): StaragazersApiClient = retrofit.create(StaragazersApiClient::class.java)
}