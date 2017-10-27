package it.carusopi.stargazers.base.di

import android.app.Application
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by carusopi on 27/10/2017.
 */
@Module
class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun providesGson() = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()

    @Provides
    @Singleton
    fun providesApplication() = application

    @Provides
    @Singleton
    fun providesResources() = application.resources

}