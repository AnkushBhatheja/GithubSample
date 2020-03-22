package com.pickletech.githubapplication.di

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.pickletech.githubapplication.view.fragment.AuthorDetailFragment
import com.pickletech.githubapplication.view.fragment.AuthorListFragment
import dagger.BindsInstance
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [GithubModule::class])
interface GithubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: AppCompatActivity): GithubComponent
    }

    fun inject(authorListFragment: AuthorListFragment)
    fun inject(authorDetailFragment: AuthorDetailFragment)

}