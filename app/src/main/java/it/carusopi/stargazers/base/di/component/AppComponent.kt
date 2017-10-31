package it.carusopi.stargazers.base.di.component

import android.app.Application
import com.google.gson.Gson
import dagger.Component
import it.carusopi.stargazers.base.di.module.*
import it.carusopi.stargazers.data.interactor.GithubInteractor
import it.carusopi.stargazers.data.model.network.GithubApiClient
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by carusopi on 27/10/2017.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, OkHttpModule::class, RetrofitModule::class, ApiModule::class, InteractorModule::class))
interface AppComponent {
    fun application(): Application
    fun gson(): Gson
    fun cache(): Cache
    fun client(): OkHttpClient
    fun loggingInterceptor(): HttpLoggingInterceptor
    fun retrofit(): Retrofit
    fun stargazersApi(): GithubApiClient
    fun stargazersInteractor(): GithubInteractor
}