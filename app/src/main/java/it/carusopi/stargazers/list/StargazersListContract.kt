package it.carusopi.stargazers.list

import it.carusopi.stargazers.base.mvp.BasePresenter
import it.carusopi.stargazers.base.mvp.BaseView
import it.carusopi.stargazers.data.model.Stargazer

/**
 * Created by carusopi on 30/10/2017.
 */
interface StargazersListContract {
    abstract class Presenter  : BasePresenter<View>() {
        abstract fun loadStargazers()
        abstract fun loadMoreStargazers()
    }

    interface View : BaseView {
        fun showStargazers(stargazersList: MutableList<Stargazer>)
        fun addMoreStargazers(stargazersList: List<Stargazer>)
//        fun addStargazers(stargazersList: List<Stargazer>)
    }
}