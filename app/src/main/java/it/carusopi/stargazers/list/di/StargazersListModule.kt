package it.carusopi.stargazers.list.di

import dagger.Module
import dagger.Provides
import it.carusopi.stargazers.base.di.ActivityScope
import it.carusopi.stargazers.data.interactor.GithubInteractor
import it.carusopi.stargazers.list.StargazersListContract
import it.carusopi.stargazers.list.StargazersListPresenter

/**
 * Created by carusopi on 30/10/2017.
 */
@Module
class StargazersListModule {
    @Provides
    @ActivityScope
    internal fun providesHomePresenter(interactor: GithubInteractor): StargazersListContract.Presenter {
        return StargazersListPresenter(interactor)
    }
}