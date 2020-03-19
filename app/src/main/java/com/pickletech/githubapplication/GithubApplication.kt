package com.pickletech.githubapplication

import android.app.Application
import com.pickletech.githubapplication.di.ApplicationComponent
import com.pickletech.githubapplication.di.DaggerApplicationComponent

class GithubApplication : Application() {

    lateinit var appComponent : ApplicationComponent

    override fun onCreate() {
        super.onCreate()

      appComponent =   DaggerApplicationComponent.builder()
            .application(this)
            .build();
    }
}