package it.carusopi.stargazers.search.di

import dagger.Module
import dagger.Provides
import it.carusopi.stargazers.base.di.ActivityScope
import it.carusopi.stargazers.search.StargazersSearchContract
import it.carusopi.stargazers.search.StargazersSearchPresenter

/**
 * Created by carusopi on 30/10/2017.
 */
@Module
class StargazersSearchModule {
    @Provides
    @ActivityScope
    internal fun providesHomePresenter(): StargazersSearchContract.Presenter {
        return StargazersSearchPresenter()
    }
}