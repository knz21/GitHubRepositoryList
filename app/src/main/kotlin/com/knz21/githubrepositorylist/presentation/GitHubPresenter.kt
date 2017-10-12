package com.knz21.githubrepositorylist.presentation

import com.knz21.githubrepositorylist.api.GitHubClient
import com.knz21.githubrepositorylist.domain.entity.GitHubRepository
import com.knz21.githubrepositorylist.store.GitHubDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GitHubPresenter @Inject constructor(private val contract: Contract, private val client: GitHubClient, private val dao: GitHubDao) {

    @JvmOverloads
    fun getRepositories(isOnCreate: Boolean = false) {
        Observable.create<List<GitHubRepository>> {
            if (isOnCreate) it.onNext(dao.findAll())
            else contract.getUserName().takeIf { it.isNotEmpty() }?.let { user ->
                client.getRepositories(user).run {
                    dao.update(this)
                    it.onNext(this)
                }
            } ?: it.onNext(listOf())
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