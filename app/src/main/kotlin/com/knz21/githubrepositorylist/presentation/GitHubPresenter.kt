package com.knz21.githubrepositorylist.presentation

import javax.inject.Inject

class GitHubPresenter @Inject constructor(private val contract: Contract) {

    fun getRepositories() {

    }

    interface Contract {
        fun showRepositories()
    }
}