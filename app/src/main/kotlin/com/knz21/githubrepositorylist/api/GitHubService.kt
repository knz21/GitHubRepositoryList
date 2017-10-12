package com.knz21.githubrepositorylist.api

import com.knz21.githubrepositorylist.domain.entity.GitHubRepository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("/users/{user}/repos")
    fun getRepositories(@Path("user") user: String): Call<List<GitHubRepository>>
}