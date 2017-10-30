package it.carusopi.stargazers.base.di

import dagger.Module
import dagger.Provides
import it.carusopi.stargazers.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by carusopi on 27/10/2017.
 */

@Module
class RetrofitModule {
    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder().baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
}