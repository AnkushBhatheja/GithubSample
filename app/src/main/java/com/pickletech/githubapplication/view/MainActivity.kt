package com.pickletech.githubapplication.view

import android.os.Bundle
import com.pickletech.githubapplication.GithubApplication
import com.pickletech.githubapplication.R
import com.pickletech.githubapplication.databinding.ActivityMainBinding
import com.pickletech.githubapplication.di.GithubComponent
import com.pickletech.githubapplication.view.baseview.BaseActivity
import com.pickletech.githubapplication.view.fragment.AuthorListFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var githubComponent: GithubComponent

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {

        githubComponent =
            ((application as GithubApplication).appComponent)
                .getGithubComponentFactory()
                .create(this)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .add(
                    mBinding.container.id,
                    AuthorListFragment()
                )
                .commit()
    }

}
