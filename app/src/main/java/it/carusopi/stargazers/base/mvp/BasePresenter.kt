package it.carusopi.stargazers.base.mvp

import java.lang.ref.WeakReference

/**
 * Created by carusopi on 26/10/2017.
 */
abstract class BasePresenter<V: BaseView>: Presenter<V>{
    private var weakReference: WeakReference<V>? = null

    override fun attachView(view: V) {
        if (!isViewAttached) {
            weakReference = WeakReference(view)
        }
    }

    override fun detachView() {
        weakReference?.clear()
        weakReference = null
    }

    val view: V?
        get() = weakReference?.get()

    val isViewAttached: Boolean
        get() = weakReference != null && weakReference!!.get() != null
}