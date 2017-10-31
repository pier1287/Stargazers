package it.carusopi.stargazers.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import it.carusopi.stargazers.R
import it.carusopi.stargazers.base.BaseActivity
import it.carusopi.stargazers.commons.recyclerview.EndlessRecyclerViewScrollListener
import it.carusopi.stargazers.data.model.Stargazer
import it.carusopi.stargazers.list.adapter.StargazersListAdapter
import it.carusopi.stargazers.list.di.DaggerStargazersListComponent
import it.carusopi.stargazers.list.di.StargazersListModule
import kotlinx.android.synthetic.main.activity_stargazers_list.*
import javax.inject.Inject

class StargazersListActivity : BaseActivity(), StargazersListContract.View{

    @Inject
    lateinit var presenter: StargazersListContract.Presenter

    lateinit var scrollListener: EndlessRecyclerViewScrollListener
    lateinit var stargazersAdapter: StargazersListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stargazers_list)
        initView()
        presenter.loadStargazers()
    }

    override fun onActivityInject() {
        DaggerStargazersListComponent.builder().appComponent(getAppcomponent())
                .stargazersListModule(StargazersListModule())
                .build()
                .inject(this)

        presenter.attachView(this)
    }


    private fun initView(){
        initRecycler()
    }

    private fun initRecycler(){
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerStargazers.layoutManager = linearLayoutManager
        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                presenter.loadMoreStargazers()
            }
        }
        recyclerStargazers.addOnScrollListener(scrollListener)
    }

    override fun showStargazers(stargazersList: MutableList<Stargazer>) {
        stargazersAdapter = StargazersListAdapter(this, stargazersList)
        recyclerStargazers.adapter = stargazersAdapter
    }

    override fun addMoreStargazers(stargazersList: List<Stargazer>) = stargazersAdapter.addStargazers(stargazersList)
}
