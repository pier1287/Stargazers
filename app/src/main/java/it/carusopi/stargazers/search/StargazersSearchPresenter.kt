package it.carusopi.stargazers.search

/**
 * Created by carusopi on 30/10/2017.
 */

internal class StargazersSearchPresenter : StargazersSearchContract.Presenter(){

    override fun onSearchClick(owner: String, repo: String) {
        view?.goToStargazersList(owner, repo)
    }
}
