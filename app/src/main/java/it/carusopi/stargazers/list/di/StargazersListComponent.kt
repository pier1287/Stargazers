package it.carusopi.stargazers.list.di

import dagger.Component
import dagger.Provides
import it.carusopi.stargazers.base.di.ActivityScope
import it.carusopi.stargazers.base.di.component.AppComponent
import it.carusopi.stargazers.list.StargazersListActivity

/**
 * Created by carusopi on 30/10/2017.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(StargazersListModule::class))
interface StargazersListComponent {
    fun inject (listActivity: StargazersListActivity)
}