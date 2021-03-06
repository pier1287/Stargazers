package it.carusopi.stargazers.search

import android.os.Bundle
import com.jakewharton.rxbinding2.view.RxView
import it.carusopi.stargazers.R
import it.carusopi.stargazers.base.BaseActivity
import it.carusopi.stargazers.list.StargazersListActivity
import it.carusopi.stargazers.search.di.DaggerStargazersSearchComponent
import it.carusopi.stargazers.search.di.StargazersSearchModule
import kotlinx.android.synthetic.main.activity_stargazers_search.*
import javax.inject.Inject

class StargazersSearchActivity : BaseActivity(), StargazersSearchContract.View {

    @Inject
    lateinit var presenter: StargazersSearchContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stargazers_search)
        initView()
    }

    override fun onActivityInject() {
        DaggerStargazersSearchComponent.builder().appComponent(getAppcomponent())
                .stargazersSearchModule(StargazersSearchModule())
                .build()
                .inject(this)

        presenter.attachView(this)
    }

    fun initView(){
        RxView.clicks(btnSearch)
//                .debounce(200, TimeUnit.MILLISECONDS)
                .subscribe {
                    presenter.onSearchClick(etextOwner.text.toString(), etextRepository.text.toString())
                }
    }

    override fun goToStargazersList(owner: String, repo: String) {
        StargazersListActivity.start(this, owner, repo)
    }

    override fun showOwnerFieldError() {
        etextOwner.error = resources.getString(R.string.required_owner)
    }

    override fun showRepoFieldError() {
        etextRepository.error = resources.getString(R.string.required_repository)
    }
}
