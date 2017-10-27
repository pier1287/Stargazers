package it.carusopi.stargazers.base.mvp

/**
 * Created by carusopi on 26/10/2017.
 */
interface Presenter<V: BaseView> {

    fun attachView(view: V)

    fun detachView()
}