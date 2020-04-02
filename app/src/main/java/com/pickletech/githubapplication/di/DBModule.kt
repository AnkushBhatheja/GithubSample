package com.pickletech.githubapplication.di

import androidx.room.Room
import com.google.gson.Gson
import com.pickletech.githubapplication.GithubApplication
import com.pickletech.githubapplication.database.Database
import com.pickletech.githubapplication.database.GithubDao
import com.pickletech.githubapplication.repo.GithubService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object DBModule {
    @Provides
    fun provideGithubDao(database: Database): GithubDao {
        return database.githubDao()
    }

    @Singleton
    @Provides
    fun provideDatabase(application: GithubApplication): Database {
        return Room
            .databaseBuilder(application.applicationContext, Database::class.java, "database.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}