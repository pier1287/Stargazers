package it.carusopi.stargazers.search

/**
 * Created by carusopi on 30/10/2017.
 */

internal class StargazersSearchPresenter : StargazersSearchContract.Presenter(){

    override fun onSearchClick(owner: String, repo: String) {
            if (owner.isBlank()) view?.showOwnerFieldError()
            if (repo.isBlank()) view?.showRepoFieldError()
            if (!owner.isBlank() && !repo.isBlank()) view?.goToStargazersList(owner, repo)
        }

}
