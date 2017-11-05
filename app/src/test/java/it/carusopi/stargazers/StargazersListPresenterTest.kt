package it.carusopi.stargazers

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argThat
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import it.carusopi.stargazers.data.interactor.GithubInteractor
import it.carusopi.stargazers.data.model.Stargazer
import it.carusopi.stargazers.data.model.StargazersPage
import it.carusopi.stargazers.list.StargazersListContract
import it.carusopi.stargazers.list.StargazersListPresenter
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class StargazersListPresenterTest {

    private val interactorMock: GithubInteractor = mock(GithubInteractor::class.java)
    private val viewMock: StargazersListContract.View = mock(StargazersListContract.View::class.java)


    @Test
    fun showStargazers_shouldBeCalled_whenWSreturnValidResponse() {
        val presenter = StargazersListPresenter(interactorMock, jobScheduler = Schedulers.trampoline(), viewScheduler = Schedulers.trampoline())
        presenter.attachView(viewMock)

        val stargazersPage = StargazersPage(listOf(
                Stargazer(id = 1),
                Stargazer(id = 2)), false)

        `when`(interactorMock.getStargazersList(any(), any())).thenReturn(Observable.just(stargazersPage))

        presenter.loadStargazers("_ignored", "_ignored")

        verify(viewMock, times(1)).addStargazers(any())
    }

    @Test
    fun stargazersListView_shouldShowCorrectListOfStargazers_whenWSreturnAListOfStargazers() {
        val presenter = StargazersListPresenter(interactorMock, jobScheduler = Schedulers.trampoline(), viewScheduler = Schedulers.trampoline())
        presenter.attachView(viewMock)

        val stargazersPage = StargazersPage(listOf(
                Stargazer(id = 1),
                Stargazer(id = 2)), false)

        `when`(interactorMock.getStargazersList(any(), any())).thenReturn(Observable.just(stargazersPage))

        presenter.loadStargazers("_ignored", "_ignored")

        verify(viewMock).addStargazers(argThat { equals(stargazersPage) })
    }

    @Test
    fun stargazersListView_shouldShowCorrectListOfStargazers_whenAreLoadedMoreStargazers() {
        val presenter = StargazersListPresenter(interactorMock, jobScheduler = Schedulers.trampoline(), viewScheduler = Schedulers.trampoline())
        presenter.attachView(viewMock)
        val stargazersPage = StargazersPage(listOf(
                Stargazer(id = 1),
                Stargazer(id = 2)), false)
        `when`(interactorMock.getMoreStargazers()).thenReturn(Observable.just(stargazersPage))

        presenter.loadMoreStargazers()

        verify(viewMock).addStargazers(argThat { equals(stargazersPage) })
    }
}

