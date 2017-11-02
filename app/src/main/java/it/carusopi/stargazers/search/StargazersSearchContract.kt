package it.carusopi.stargazers.search

import android.support.annotation.StringRes
import it.carusopi.stargazers.base.mvp.BasePresenter
import it.carusopi.stargazers.base.mvp.BaseView

/**
 * Created by carusopi on 26/10/2017.
 */
interface StargazersSearchContract {

    abstract class Presenter  : BasePresenter<View>() {
        abstract fun onSearchClick(owner: String, repo: String)
    }

    interface View : BaseView {
        fun goToStargazersList(owner: String, repo: String)
        fun showOwnerFieldError()
        fun showRepoFieldError()
    }
}