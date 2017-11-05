package it.carusopi.stargazers

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argThat
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import it.carusopi.stargazers.data.interactor.GithubInteractorImpl
import it.carusopi.stargazers.data.model.Stargazer
import it.carusopi.stargazers.data.model.StargazersPage
import it.carusopi.stargazers.data.model.network.GithubApiClient
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response

class GithubInteractorImplTest {

    val apiServiceMock = mock(GithubApiClient::class.java)
    val githubInteractor = GithubInteractorImpl(apiServiceMock)

    @Test
    fun callWsWithCorrectParams() {
        val expectedOwner = "owner"
        val expectedRepository = "repository"
        `when`(apiServiceMock.getStargazers(any(), any())).thenReturn(Observable.just(Response.success(mutableListOf())))

        githubInteractor.getStargazersList(expectedOwner, expectedRepository)

        verify(apiServiceMock).getStargazers(argThat { equals(expectedOwner) }, argThat { equals(expectedRepository) })
    }

    @Test
    fun callWsReturnCorrectList() {
        val expectedList = listOf(Stargazer(id = 1))
        `when`(apiServiceMock.getStargazers(any(), any())).thenReturn(Observable.just(Response.success(expectedList)))

        val stargazersPage: StargazersPage = githubInteractor.getStargazersList("_ingored", "_ignored").test().values().first()

        assertEquals(expectedList, stargazersPage.stargazersList)
    }

}