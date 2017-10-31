package it.carusopi.stargazers.list

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

import it.carusopi.stargazers.data.interactor.GithubInteractor

/**
 * Created by carusopi on 30/10/2017.
 */

class StargazersListPresenter @Inject constructor(var githubInteractor: GithubInteractor) : StargazersListContract.Presenter() {

    override fun loadStargazers(owner: String, repo: String) {
        githubInteractor.getStargazersList(owner, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { stargazersList -> view?.showStargazers(stargazersList) },
                        { })
    }

    override fun loadMoreStargazers() {
        githubInteractor.getMoreStargazers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { stargazersList -> stargazersList?.let { view?.addMoreStargazers(stargazersList) } },
                        { })
    }
}
