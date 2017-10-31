package it.carusopi.stargazers.base.di.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import it.carusopi.stargazers.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by carusopi on 27/10/2017.
 */

@Module
class RetrofitModule {
    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient, gson: Gson): Retrofit =
            Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
}