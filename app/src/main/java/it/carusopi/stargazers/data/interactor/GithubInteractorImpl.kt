package it.carusopi.stargazers.data.interactor

import io.reactivex.Observable
import it.carusopi.stargazers.data.model.Stargazer
import it.carusopi.stargazers.data.model.network.GithubApiClient
import javax.inject.Inject

/**
 * Created by carusopi on 30/10/2017.
 */
class GithubInteractorImpl @Inject constructor(var githubApi: GithubApiClient): GithubInteractor {


    override fun getStargazersList(owner: String, repository: String): Observable<List<Stargazer>> {
        return githubApi.getStargazers(owner, repository)
    }
}