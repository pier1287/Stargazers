package it.carusopi.stargazers.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import it.carusopi.stargazers.R
import it.carusopi.stargazers.base.BaseActivity
import it.carusopi.stargazers.data.model.Stargazer
import it.carusopi.stargazers.list.adapter.StargazersListAdapter
import it.carusopi.stargazers.list.di.DaggerStargazersListComponent
import it.carusopi.stargazers.list.di.StargazersListModule
import kotlinx.android.synthetic.main.activity_stargazers_list.*
import javax.inject.Inject

class StargazersListActivity : BaseActivity(), StargazersListContract.View{

    @Inject
    lateinit var presenter: StargazersListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stargazers_list)

        presenter.loadStargazers()
    }

    override fun onActivityInject() {
        DaggerStargazersListComponent.builder().appComponent(getAppcomponent())
                .stargazersListModule(StargazersListModule())
                .build()
                .inject(this)

        presenter.attachView(this)
    }

    override fun showStargazers(stargazersList: List<Stargazer>) {
        recyclerStargazers.adapter = StargazersListAdapter(this, stargazersList)
        recyclerStargazers.layoutManager = LinearLayoutManager(this)
    }
}
