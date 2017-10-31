package it.carusopi.stargazers.data.interactor

import io.reactivex.Observable
import it.carusopi.stargazers.data.model.Stargazer
import it.carusopi.stargazers.data.model.exception.StargazersException
import it.carusopi.stargazers.data.model.network.GithubApiClient
import it.carusopi.stargazers.data.network.PageLinks
import javax.inject.Inject

/**
 * Created by carusopi on 30/10/2017.
 */
class GithubInteractorImpl @Inject constructor(var githubApi: GithubApiClient): GithubInteractor {

    var pageLinks: PageLinks? = null

    override fun getStargazersList(owner: String, repository: String): Observable<MutableList<Stargazer>> {
        return githubApi.getStargazers(owner, repository).map {
            pageLinks = PageLinks(it.headers())
            it.body()
        }
    }

    override fun getMoreStargazers(): Observable<List<Stargazer>?> {
        val page = pageLinks ?: throw StargazersException("You have to call getStargazersList() at least once")

        val next = page.next
        if (next != null) {
            return githubApi.getMoreStargazers(next).map {
                pageLinks = PageLinks(it.headers())
                it.body()
            }
        }
        return Observable.just(null)
    }
}