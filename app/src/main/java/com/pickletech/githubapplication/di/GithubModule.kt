package com.pickletech.githubapplication.di

import com.pickletech.githubapplication.repo.GithubRepository
import com.pickletech.githubapplication.repo.GithubRepositoryImpl
import com.pickletech.githubapplication.repo.GithubService
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [ViewModelModule::class])
abstract class GithubModule {
    @Binds
    abstract fun bindGithubRepository(githubRepository: GithubRepositoryImpl): GithubRepository

}