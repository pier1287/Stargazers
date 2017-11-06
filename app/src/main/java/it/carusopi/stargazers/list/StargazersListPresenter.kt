package it.carusopi.stargazers.list

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import it.carusopi.stargazers.R
import it.carusopi.stargazers.data.interactor.GithubInteractor
import it.carusopi.stargazers.data.model.StargazersPage
import it.carusopi.stargazers.data.model.exception.HttpNotFoundException
import javax.inject.Inject

/**
 * Created by carusopi on 30/10/2017.
 */

class StargazersListPresenter @Inject constructor(private var githubInteractor: GithubInteractor)
    : StargazersListContract.Presenter() {

    override fun loadStargazers(owner: String, repo: String) {
        view?.showListLoading()
        githubInteractor.getStargazersList(owner, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { onGetStargazersListSuccess(it) },
                        { onGetStargazersListError(it) })
    }

    private val onGetStargazersListSuccess = { stargazersPage: StargazersPage ->
        if (stargazersPage.isEmpty()) {
            view?.showListEmpty()
        } else {
            view?.addStargazers(stargazersPage)
            view?.showStargazers()
        }
        view?.hideListLoading()
    }

    private val onGetStargazersListError = { ex: Throwable ->
        when (ex) {
            is HttpNotFoundException -> view?.showListError(R.string.err_not_found_list)
            else -> view?.showListError(R.string.err_generic)
        }
        view?.hideListLoading()
    }

    override fun loadMoreStargazers() {
        githubInteractor.getMoreStargazers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { stargazersPage -> stargazersPage?.let { view?.addStargazers(stargazersPage) } },
                        { view?.showListError(R.string.err_generic) })
    }
}
