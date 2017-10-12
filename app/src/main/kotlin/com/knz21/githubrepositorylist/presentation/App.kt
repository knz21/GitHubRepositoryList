package com.knz21.githubrepositorylist.presentation

import android.app.Application
import com.knz21.githubrepositorylist.di.ApplicationComponent
import com.knz21.githubrepositorylist.di.ApplicationModule
import com.knz21.githubrepositorylist.di.DaggerApplicationComponent

class App : Application() {
    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}