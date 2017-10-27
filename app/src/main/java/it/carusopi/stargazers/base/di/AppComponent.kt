package it.carusopi.stargazers.base.di

import android.app.Application
import com.google.gson.Gson
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by carusopi on 27/10/2017.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun application(): Application
    fun gson(): Gson
    fun retrofit(): Retrofit
}