package com.pickletech.githubapplication.di

import androidx.room.Room
import com.pickletech.githubapplication.GithubApplication
import com.pickletech.githubapplication.database.Database
import com.pickletech.githubapplication.database.GithubDao
import com.pickletech.githubapplication.repo.GithubService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
object ApplicationModule {

    @Provides
    fun provideGithubService(retrofit: Retrofit): GithubService {
        return retrofit.create(GithubService::class.java)
    }

    @Provides
    fun provideGithubDao(database: Database): GithubDao {
        return database.githubDao()
    }

    @Provides
    fun provideDatabase(application: GithubApplication): Database {
        return Room
            .databaseBuilder(application.applicationContext, Database::class.java, "database.db")
            .fallbackToDestructiveMigration()
            .build()
    }


}