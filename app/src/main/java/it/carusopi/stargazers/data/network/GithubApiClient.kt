package it.carusopi.stargazers.data.model.network

import io.reactivex.Observable
import it.carusopi.stargazers.data.model.Stargazer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import java.util.*

/**
 * Created by carusopi on 27/10/2017.
 */

interface GithubApiClient {
    @GET("/repos/{owner}/{repo}/stargazers")
    fun getStargazers(@Path("owner") owner: String, @Path("repo") repository: String) : Observable<Response<MutableList<Stargazer>>>

    @GET
    fun getMoreStargazers(@Url url:String): Observable<Response<List<Stargazer>>>
}