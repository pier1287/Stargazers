package it.carusopi.stargazers.search.di

import dagger.Component
import it.carusopi.stargazers.base.di.ActivityScope
import it.carusopi.stargazers.base.di.component.AppComponent
import it.carusopi.stargazers.search.StargazersSearchActivity

/**
 * Created by carusopi on 30/10/2017.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(StargazersSearchModule::class))
interface StargazersSearchComponent {
    fun inject (searchActivity: StargazersSearchActivity)
}