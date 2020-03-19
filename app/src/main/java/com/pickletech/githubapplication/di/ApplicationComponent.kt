package com.pickletech.githubapplication.di

import com.pickletech.githubapplication.view.fragment.AuthorListFragment
import com.pickletech.githubapplication.GithubApplication
import com.pickletech.githubapplication.view.fragment.AuthorDetailFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, GithubModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: GithubApplication): Builder

        fun build(): ApplicationComponent

    }

    fun inject(authorListFragment: AuthorListFragment)
    fun inject(authorDetailFragment: AuthorDetailFragment)
}