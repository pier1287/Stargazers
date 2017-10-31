package it.carusopi.stargazers.search

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import it.carusopi.stargazers.R
import kotlinx.android.synthetic.main.activity_stargazers_search.*
import javax.inject.Inject

class StargazersSearchActivity : AppCompatActivity(), StargazersSearchContract.View {

    @Inject
    lateinit var mPresenter: StargazersSearchContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stargazers_search)
        initView()
    }

    fun initView(){}

    override fun goToStargazersList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
