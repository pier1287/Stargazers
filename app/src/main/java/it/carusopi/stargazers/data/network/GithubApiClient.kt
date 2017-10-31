package it.carusopi.stargazers.data.model.network

import io.reactivex.Observable
import it.carusopi.stargazers.data.model.Stargazer
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

/**
 * Created by carusopi on 27/10/2017.
 */

interface GithubApiClient {
    @GET("/repos/{owner}/{repo}/stargazers")
    fun getStargazers(@Path("owner") owner: String, @Path("repo") repository: String) : Observable<List<Stargazer>>
}