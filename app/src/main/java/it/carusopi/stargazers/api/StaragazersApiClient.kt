package it.carusopi.stargazers.api

import android.database.Observable
import it.carusopi.stargazers.list.model.StargazersModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by carusopi on 27/10/2017.
 */

interface StaragazersApiClient {
    @GET("/repos/{owner}/{repo}/stargazers")
    fun getStargazers(@Path("owner") owner: String, @Path("repo") repository: String) : Call<StargazersModel>
}