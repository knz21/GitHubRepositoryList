package com.knz21.githubrepositorylist.api

import com.knz21.githubrepositorylist.domain.entity.GitHubRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubClient @Inject constructor(private val service: GitHubService) {

    fun getRepositories(user: String): List<GitHubRepository> =
            service.getRepositories(user).execute().body() ?: listOf()
}
