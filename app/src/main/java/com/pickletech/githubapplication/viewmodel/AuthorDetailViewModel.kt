package com.pickletech.githubapplication.viewmodel

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.pickletech.githubapplication.GithubApplication
import com.pickletech.githubapplication.R
import com.pickletech.githubapplication.model.Author
import com.pickletech.githubapplication.model.SearchResult
import com.pickletech.githubapplication.repo.GithubRepository
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class AuthorDetailViewModel
@Inject constructor(
    var application: GithubApplication
) : BaseViewModel(application)