package com.sniper.social.home.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.sniper.social.base.adapter.OnItemClickListener
import com.sniper.social.base.di.Scope
import com.sniper.social.base.intents.IntentExtra
import com.sniper.social.base.intents.ScreenLink
import com.sniper.social.base.services.AppLinksService
import com.sniper.social.base.ui.BaseActivity
import com.sniper.social.converter.posts.PostViewModel
import com.sniper.social.home.R
import com.sniper.social.home.di.homeModule
import com.sniper.social.home.mvp.HomePresenter
import com.sniper.social.home.ui.adapter.PostsAdapter
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject
import org.koin.dsl.module.Module

class HomeActivity : BaseActivity<HomePresenter>(), HomePresenter.View, OnItemClickListener<PostViewModel> {

    override val presenter: HomePresenter by inject()

    override val screenLayout: Int = R.layout.activity_home

    override val screenModule: Module = homeModule

    override val screenScope: String = Scope.HOME

    private val appLinksService: AppLinksService by inject()
    private val postsAdapter = PostsAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initScreenAdapter()
        presenter.attachView(this)
        presenter.fetchPosts()
    }

    override fun showPosts(postsList: List<PostViewModel>) {
        postsAdapter.setNewItems(postsList)
    }

    override fun showError(errorMessage: String) {
        displayError(errorMessage)
    }

    override fun showLoading(show: Boolean) {
        progress_indicator.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onItemClick(selectedItem: PostViewModel) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(appLinksService.generateScreenLink(ScreenLink.DETAILS)))
        intent.putExtra(IntentExtra.POST_EXTRA, selectedItem)
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            showError("If you use Instant version of the app please install the full version")
        }
    }

    private fun initScreenAdapter() {
        with(posts_recycler_view) {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
            adapter = postsAdapter
        }
    }
}
