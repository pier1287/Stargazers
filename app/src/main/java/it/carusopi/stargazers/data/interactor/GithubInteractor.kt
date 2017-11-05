package it.carusopi.stargazers.data.interactor

import io.reactivex.Observable
import it.carusopi.stargazers.data.model.StargazersPage

/**
 * Created by carusopi on 30/10/2017.
 */
interface GithubInteractor {
   fun getStargazersList(owner: String, repository: String): Observable<StargazersPage>
   fun getMoreStargazers(): Observable<StargazersPage?>
}