package com.knz21.githubrepositorylist.domain.entity

import com.github.gfx.android.orma.annotation.Column
import com.github.gfx.android.orma.annotation.PrimaryKey
import com.github.gfx.android.orma.annotation.Setter
import com.github.gfx.android.orma.annotation.Table

@Table
data class GitHubRepository(
        @PrimaryKey
        @Setter("id")
        var id: String,
        @Column
        @Setter("name")
        var name: String
)