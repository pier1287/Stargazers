package it.carusopi.stargazers.base

import android.app.Application
import it.carusopi.stargazers.base.di.AppComponent
import it.carusopi.stargazers.base.di.AppModule
import it.carusopi.stargazers.base.di.DaggerAppComponent

/**
 * Created by carusopi on 27/10/2017.
 */
class StargazersApp: Application() {

    companion object{
        @JvmStatic lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}