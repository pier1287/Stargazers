package it.carusopi.stargazers.base.di.module

import dagger.Module
import dagger.Provides
import it.carusopi.stargazers.data.model.network.GithubApiClient
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by carusopi on 27/10/2017.
 */
@Module
class ApiModule {
    @Provides
    @Singleton
    fun providesEndpoints(retrofit: Retrofit): GithubApiClient = retrofit.create(GithubApiClient::class.java)
}