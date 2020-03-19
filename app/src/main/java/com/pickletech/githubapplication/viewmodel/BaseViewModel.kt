package com.pickletech.githubapplication.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pickletech.githubapplication.GithubApplication
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.*

open class BaseViewModel(application: GithubApplication) : AndroidViewModel(application) {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val mLoading: MutableLiveData<Pair<Boolean, String?>> = MutableLiveData()

    val mShowMessage: MutableLiveData<String> = MutableLiveData()


    protected fun addDisposable(observable: Disposable) {
        compositeDisposable.add(observable)
    }


}