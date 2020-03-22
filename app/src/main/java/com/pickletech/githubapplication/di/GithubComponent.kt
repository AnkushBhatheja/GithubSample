package com.pickletech.githubapplication.di

import com.pickletech.githubapplication.view.fragment.AuthorDetailFragment
import com.pickletech.githubapplication.view.fragment.AuthorListFragment
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [GithubModule::class])
interface GithubComponent {

    fun inject(authorListFragment: AuthorListFragment)
    fun inject(authorDetailFragment: AuthorDetailFragment)

}