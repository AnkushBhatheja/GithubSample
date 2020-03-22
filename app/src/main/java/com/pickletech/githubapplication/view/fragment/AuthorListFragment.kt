package com.pickletech.githubapplication.view.fragment

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pickletech.githubapplication.GithubApplication
import com.pickletech.githubapplication.R
import com.pickletech.githubapplication.databinding.FragmentAuthorListBinding
import com.pickletech.githubapplication.model.Author
import com.pickletech.githubapplication.view.ItemClickListener
import com.pickletech.githubapplication.view.MainActivity
import com.pickletech.githubapplication.view.adapter.AuthorAdapter
import com.pickletech.githubapplication.view.baseview.BaseFragment
import com.pickletech.githubapplication.viewmodel.AuthorListViewModel
import com.pickletech.githubapplication.viewmodel.ViewModelFactory
import javax.inject.Inject


class AuthorListFragment : BaseFragment<FragmentAuthorListBinding, AuthorListViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun layoutId(): Int {
        return R.layout.fragment_author_list
    }

    override fun inject() {
        (activity as MainActivity).githubComponent
            .inject(this)
    }

    override fun createViewModel(): AuthorListViewModel {
        return ViewModelProvider(this, viewModelFactory)
            .get(AuthorListViewModel::class.java)
    }

    override fun initView(savedInstanceState: Bundle?) {

        mBinding.viewModel = viewModel
        mBinding.recycleView.layoutManager = LinearLayoutManager(context)

        val adapter =
            AuthorAdapter(object :
                ItemClickListener<Author> {
                override fun onItemClick(item: Author) {
                    val bundle = Bundle()
                    bundle.putString("web_url", item.pageUrl)

                    val fragment = AuthorDetailFragment()
                    fragment.arguments = bundle

                    fragmentManager?.beginTransaction()
                        ?.add(R.id.container, fragment)
                        ?.addToBackStack(javaClass.name)
                        ?.commit()

                }

                override fun showMore() {
                    viewModel.fetchAuthorsFromApi(
                        viewModel.authorData.value?.first ?: "alphabetagama"
                    )
                }
            })
        mBinding.recycleView.adapter = adapter


        viewModel.authorData.observe(this, Observer {
            adapter.addAll(it.second)
        })


        val handler = Handler()
        val runnable = Runnable() {
            kotlin.run {
                viewModel.fetchAuthors(viewModel.searchKey.value)
            }
        }

        viewModel.searchKey.observe(this, Observer {
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, 600)
        })

        if (viewModel.authorData.value != null) {
            viewModel.authorData.value?.second?.let { adapter.addAll(it) }
        } else
            viewModel.fetchAuthors(viewModel.searchKey.value)

    }

}
