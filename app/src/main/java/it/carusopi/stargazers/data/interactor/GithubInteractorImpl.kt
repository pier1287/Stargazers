package it.carusopi.stargazers.data.interactor

import io.reactivex.Observable
import it.carusopi.stargazers.data.model.StargazersPage
import it.carusopi.stargazers.data.model.exception.StargazersException
import it.carusopi.stargazers.data.model.network.GithubApiClient
import it.carusopi.stargazers.data.network.PageLinks
import javax.inject.Inject

/**
 * Created by carusopi on 30/10/2017.
 */
class GithubInteractorImpl @Inject constructor(private var githubApi: GithubApiClient) : GithubInteractor {

    private var pageLinks: PageLinks? = null

    override fun getStargazersList(owner: String, repository: String): Observable<StargazersPage> {
        return githubApi.getStargazers(owner, repository).map {
            pageLinks = PageLinks(it.headers())
            StargazersPage(it.body() ?: emptyList(), pageLinks?.next != null)
        }
    }

    override fun getMoreStargazers(): Observable<StargazersPage?> {

        pageLinks?.let { page ->
            return page.next?.let { next ->
                githubApi.getMoreStargazers(next).map {
                    pageLinks = PageLinks(it.headers())
                    StargazersPage(it.body() ?: emptyList(), pageLinks?.next != null)
                }
            } ?: Observable.just(StargazersPage(emptyList(), false))
        } ?: throw StargazersException("You have to call getStargazersList() at least once")

    }
}