package it.carusopi.stargazers.data.model

/**
 * Created by carusopi on 03/11/2017.
 */

data class StargazersPage(val stargazersList: List<Stargazer>, val hasNextPage: Boolean){
    fun isEmpty() = stargazersList.isEmpty()
}