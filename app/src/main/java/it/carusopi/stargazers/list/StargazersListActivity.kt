package it.carusopi.stargazers.list

import android.content.Context
import android.content.Intent
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

    lateinit private var repo: String
    lateinit private var owner: String

    companion object {
        private val ARG_OWNER = "ARG_OWNER"
        private val ARG_REPO = "ARG_REPO"

        fun start(context: Context, owner: String, repository: String) {
            val intent = Intent(context, StargazersListActivity::class.java)
            intent.putExtra(ARG_OWNER, owner)
            intent.putExtra(ARG_REPO, repository)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtra()

        setContentView(R.layout.activity_stargazers_list)
        initView()

        presenter.loadStargazers(owner, repo)
    }

    private fun getExtra(){
        repo = intent.getStringExtra(ARG_REPO)
        owner = intent.getStringExtra(ARG_OWNER)
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
