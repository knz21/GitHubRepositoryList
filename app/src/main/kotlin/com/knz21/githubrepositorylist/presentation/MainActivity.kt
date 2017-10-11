package com.knz21.githubrepositorylist.presentation

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.knz21.githubrepositorylist.R
import com.knz21.githubrepositorylist.databinding.ActivityMainBinding
import com.knz21.githubrepositorylist.di.MainModule
import com.knz21.githubrepositorylist.view.adapter.RepositoryListAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), GitHubPresenter.Contract {
    private val binding by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main) }
    private val adapter = RepositoryListAdapter()
    @Inject lateinit var presenter: GitHubPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).component.plus(MainModule(this)).inject(this)
        binding.presenter = presenter
        binding.repositoryList.adapter = adapter
    }

    override fun showRepositories() {

    }
}
