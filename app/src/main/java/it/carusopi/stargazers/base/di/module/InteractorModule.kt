package it.carusopi.stargazers.base.di.module

import dagger.Module
import dagger.Provides
import it.carusopi.stargazers.data.interactor.GithubInteractor
import it.carusopi.stargazers.data.interactor.GithubInteractorImpl
import it.carusopi.stargazers.data.model.network.GithubApiClient
import javax.inject.Singleton

/**
 * Created by carusopi on 30/10/2017.
 */
@Module
class InteractorModule {
    @Provides
    @Singleton
    fun providesStargazersInteractor(githubApiClient: GithubApiClient): GithubInteractor = GithubInteractorImpl(githubApiClient)
}