package com.pickletech.githubapplication.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pickletech.githubapplication.viewmodel.AuthorDetailViewModel
import com.pickletech.githubapplication.viewmodel.AuthorListViewModel
import com.pickletech.githubapplication.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthorListViewModel::class)
    abstract fun bindAuthorListViewModel(repoViewModel: AuthorListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthorDetailViewModel::class)
    abstract fun bindAuthorDetailViewModel(repoViewModel: AuthorDetailViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}