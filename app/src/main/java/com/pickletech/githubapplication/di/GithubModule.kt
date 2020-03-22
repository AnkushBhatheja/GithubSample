package com.pickletech.githubapplication.di

import com.pickletech.githubapplication.database.Database
import com.pickletech.githubapplication.database.GithubDao
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


    companion object {
        @PerActivity
        @Provides
        fun provideGithubService(retrofit: Retrofit): GithubService {
            return retrofit.create(GithubService::class.java)
        }

        @PerActivity
        @Provides
        fun provideGithubDao(database: Database): GithubDao {
            return database.githubDao()
        }
    }

}