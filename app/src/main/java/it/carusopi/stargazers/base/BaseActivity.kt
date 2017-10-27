package it.carusopi.stargazers.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import it.carusopi.stargazers.base.mvp.BaseView

/**
 * Created by carusopi on 27/10/2017.
 */
abstract class BaseActivity: AppCompatActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onActivityInject()
    }

    protected abstract fun onActivityInject()
}