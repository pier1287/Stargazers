package it.carusopi.stargazers.search

import it.carusopi.stargazers.base.mvp.BasePresenter
import it.carusopi.stargazers.base.mvp.BaseView

/**
 * Created by carusopi on 26/10/2017.
 */
interface StargazersSearchContract {

    abstract class Presenter  : BasePresenter<View>() {

    }

    interface View : BaseView {
        fun goToStargazersList()
    }
}