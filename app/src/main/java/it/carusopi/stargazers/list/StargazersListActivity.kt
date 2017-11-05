package it.carusopi.stargazers.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import it.carusopi.stargazers.R
import it.carusopi.stargazers.base.BaseActivity
import it.carusopi.stargazers.commons.recyclerview.EndlessRecyclerViewScrollListener
import it.carusopi.stargazers.data.model.StargazersPage
import it.carusopi.stargazers.list.adapter.StargazersListAdapter
import it.carusopi.stargazers.list.di.DaggerStargazersListComponent
import it.carusopi.stargazers.list.di.StargazersListModule
import kotlinx.android.synthetic.main.activity_stargazers_list.*
import javax.inject.Inject

class StargazersListActivity : BaseActivity(), StargazersListContract.View {

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

    private fun getExtra() {
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


    private fun initView() {
        initRecycler()
    }

    private fun initRecycler() {
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerStargazers.layoutManager = linearLayoutManager
        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                presenter.loadMoreStargazers()
            }
        }
        recyclerStargazers.addOnScrollListener(scrollListener)

        stargazersAdapter = StargazersListAdapter(this)
        recyclerStargazers.adapter = stargazersAdapter
    }

    override fun addStargazers(stargazersPage: StargazersPage) = stargazersAdapter.addStargazers(stargazersPage)

    override fun showStargazers() { recyclerStargazers.visibility = View.VISIBLE }
    override fun hideStargazers() { recyclerStargazers.visibility = View.GONE }

    override fun showListLoading() { progress_loader.visibility = View.VISIBLE }
    override fun hideListLoading() { progress_loader.visibility = View.GONE }

    override fun showListError(messageRes: Int) { message.text = resources.getText(messageRes); message.visibility = View.VISIBLE }
    override fun hideListError() { message.visibility = View.GONE}

    override fun showListEmpty() { message.text = resources.getText(R.string.err_empty_list); message.visibility = View.VISIBLE}
    override fun hideListEmpty() { message.visibility = View.GONE}
}
