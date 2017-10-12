package com.knz21.githubrepositorylist.store

import com.github.gfx.android.orma.annotation.OnConflict
import com.knz21.githubrepositorylist.di.ApplicationModule
import com.knz21.githubrepositorylist.domain.entity.GitHubRepository
import com.knz21.githubrepositorylist.domain.entity.OrmaDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubDao @Inject constructor(ormaHolder: ApplicationModule.OrmaHolder) {
    private val db: OrmaDatabase = ormaHolder.db

    fun findAll(): List<GitHubRepository> = db.selectFromGitHubRepository().toList()

    fun update(repositories: List<GitHubRepository>) {
        db.transactionSync {
            db.deleteAll()
            db.prepareInsertIntoGitHubRepository(OnConflict.REPLACE).executeAll(repositories)
        }
    }
}
