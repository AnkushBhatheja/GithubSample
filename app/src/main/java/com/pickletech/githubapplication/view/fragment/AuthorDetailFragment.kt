package com.pickletech.githubapplication.view.fragment

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pickletech.githubapplication.GithubApplication
import com.pickletech.githubapplication.R
import com.pickletech.githubapplication.databinding.FragmentAuthorDetailsBinding
import com.pickletech.githubapplication.databinding.FragmentAuthorListBinding
import com.pickletech.githubapplication.model.Author
import com.pickletech.githubapplication.view.ItemClickListener
import com.pickletech.githubapplication.view.adapter.AuthorAdapter
import com.pickletech.githubapplication.view.baseview.BaseFragment
import com.pickletech.githubapplication.viewmodel.AuthorDetailViewModel
import com.pickletech.githubapplication.viewmodel.AuthorListViewModel
import com.pickletech.githubapplication.viewmodel.BaseViewModel
import com.pickletech.githubapplication.viewmodel.ViewModelFactory
import javax.inject.Inject


class AuthorDetailFragment : BaseFragment<FragmentAuthorDetailsBinding, AuthorDetailViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun layoutId(): Int {
        return R.layout.fragment_author_details
    }

    override fun inject() {
        (activity?.application as GithubApplication).appComponent
            .inject(this)
    }

    override fun createViewModel(): AuthorDetailViewModel {
        return ViewModelProvider(this, viewModelFactory)
            .get(AuthorDetailViewModel::class.java)
    }

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.url = arguments?.getString("web_url")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> activity?.onBackPressed()
        }
        return true
    }
}
