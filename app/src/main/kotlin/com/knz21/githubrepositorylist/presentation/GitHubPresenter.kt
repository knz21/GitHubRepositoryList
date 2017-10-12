package com.knz21.githubrepositorylist.presentation

import com.knz21.githubrepositorylist.api.GitHubClient
import com.knz21.githubrepositorylist.domain.entity.GitHubRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GitHubPresenter @Inject constructor(private val contract: Contract, private val client: GitHubClient) {

    fun getRepositories() {
        Observable.create<List<GitHubRepository>> {
            it.onNext(client.getRepositories(contract.getUserName()))
            it.onComplete()
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { contract.showRepositories(it) }
    }

    interface Contract {
        fun showRepositories(repositories: List<GitHubRepository>)

        fun getUserName(): String
    }
}