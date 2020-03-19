package com.pickletech.githubapplication.view

import android.os.Bundle
import com.pickletech.githubapplication.R
import com.pickletech.githubapplication.databinding.ActivityMainBinding
import com.pickletech.githubapplication.view.baseview.BaseActivity
import com.pickletech.githubapplication.view.fragment.AuthorListFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .add(
                    mBinding.container.id,
                    AuthorListFragment()
                )
                .commit()
    }

}
