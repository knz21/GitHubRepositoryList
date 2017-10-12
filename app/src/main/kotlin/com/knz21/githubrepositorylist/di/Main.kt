package com.knz21.githubrepositorylist.di

import com.knz21.githubrepositorylist.presentation.GitHubPresenter
import com.knz21.githubrepositorylist.presentation.MainActivity
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class MainModule(private val contract: GitHubPresenter.Contract) {

    @Provides
    fun provideComponent() = contract
}

@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(a: MainActivity)
}