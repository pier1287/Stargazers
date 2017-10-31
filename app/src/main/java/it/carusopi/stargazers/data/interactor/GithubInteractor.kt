package it.carusopi.stargazers.data.interactor

import io.reactivex.Observable
import it.carusopi.stargazers.data.model.Stargazer

/**
 * Created by carusopi on 30/10/2017.
 */
interface GithubInteractor {
   fun getStargazersList(owner: String, repository: String): Observable<MutableList<Stargazer>>
   fun getMoreStargazers(): Observable<List<Stargazer>?>
}