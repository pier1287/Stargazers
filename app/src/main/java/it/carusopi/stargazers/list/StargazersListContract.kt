package it.carusopi.stargazers.list

import android.support.annotation.StringRes
import it.carusopi.stargazers.base.mvp.BasePresenter
import it.carusopi.stargazers.base.mvp.BaseView
import it.carusopi.stargazers.data.model.StargazersPage

/**
 * Created by carusopi on 30/10/2017.
 */
interface StargazersListContract {
    abstract class Presenter : BasePresenter<View>() {
        abstract fun loadStargazers(owner: String, repo: String)
        abstract fun loadMoreStargazers()
    }

    interface View : BaseView {
        fun addStargazers(stargazersPage: StargazersPage)
        fun showStargazers()
        fun hideStargazers()
        fun showListLoading()
        fun hideListLoading()
        fun showListError(@StringRes messageRes: Int)
        fun hideListError()
        fun showListEmpty()
        fun hideListEmpty()
    }
}