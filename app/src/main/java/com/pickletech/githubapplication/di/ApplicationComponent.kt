package com.pickletech.githubapplication.di

import androidx.room.RoomDatabase
import com.pickletech.githubapplication.view.fragment.AuthorListFragment
import com.pickletech.githubapplication.GithubApplication
import com.pickletech.githubapplication.database.Database
import com.pickletech.githubapplication.view.fragment.AuthorDetailFragment
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: GithubApplication): Builder

        fun build(): ApplicationComponent

    }

    fun getGithubComponentFactory(): GithubComponent.Factory

}