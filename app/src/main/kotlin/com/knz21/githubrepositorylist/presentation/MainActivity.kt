package com.knz21.githubrepositorylist.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.knz21.githubrepositorylist.R
import com.knz21.githubrepositorylist.di.MainModule
import javax.inject.Inject

class MainActivity : AppCompatActivity(), GitHubPresenter.Contract{
    @Inject lateinit var presenter: GitHubPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).component.plus(MainModule(this)).inject(this)
        setContentView(R.layout.activity_main)
    }
}
