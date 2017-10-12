package com.knz21.githubrepositorylist.di

import com.github.gfx.android.orma.AccessThreadConstraint
import com.knz21.githubrepositorylist.api.GitHubService
import com.knz21.githubrepositorylist.domain.entity.OrmaDatabase
import com.knz21.githubrepositorylist.presentation.App
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val applicationContext: App) {

    @Provides
    fun providesOkHttp(): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
                    .build()


    @Provides
    fun provideRetrofit(oktHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .client(oktHttpClient)
                    .baseUrl("https://api.github.com")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

    @Provides
    fun provideGitHubService(retrofit: Retrofit): GitHubService = retrofit.create(GitHubService::class.java)

    @Provides
    fun provideOrma(): OrmaHolder =
            OrmaHolder(OrmaDatabase.builder(applicationContext)
                    .writeOnMainThread(AccessThreadConstraint.FATAL)
                    .readOnMainThread(AccessThreadConstraint.FATAL)
                    .trace(true)
                    .build())

    class OrmaHolder(val db: OrmaDatabase)
}

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun plus(m: MainModule): MainComponent
}