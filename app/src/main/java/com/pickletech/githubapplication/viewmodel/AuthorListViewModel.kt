package com.pickletech.githubapplication.viewmodel

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.pickletech.githubapplication.GithubApplication
import com.pickletech.githubapplication.R
import com.pickletech.githubapplication.model.Author
import com.pickletech.githubapplication.model.SearchResult
import com.pickletech.githubapplication.repo.GithubRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthorListViewModel
@Inject constructor(
    var application: GithubApplication,
    var repository: GithubRepository
) : BaseViewModel(application) {

    private val publishSubject: PublishSubject<String?> = PublishSubject.create<String?>()

    val searchKey: MutableLiveData<String?> by lazy {
        MutableLiveData<String?>("")
    }

    val authorData: MutableLiveData<List<Author>> by lazy {
        MutableLiveData<List<Author>>()
    }


    fun search(key: String) {
        publishSubject.onNext(key)
    }

    fun fetchAuthorsFromApi(query: String?) {
        repository.fetchAuthorFromApi(query)
            .subscribe(object : SingleObserver<SearchResult> {
                override fun onSuccess(t: SearchResult) {
                    mLoading.postValue(Pair(false, null))
                    authorData.postValue(t.items)

                    repository.saveAuthors(t.items);
                }

                override fun onSubscribe(d: Disposable) {
                    mLoading.postValue(Pair(true, application.getString(R.string.loading)))
                }

                override fun onError(e: Throwable) {
                    mLoading.postValue(Pair(false, null))
                    mShowMessage.postValue(e.message)
                }
            })
    }


    fun fetchAuthors() {
        addDisposable(publishSubject.debounce(400, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                repository.fetchAuthor(it)
                    .subscribe(object : SingleObserver<MutableList<Author>> {
                        override fun onSuccess(t: MutableList<Author>) {
                            mLoading.postValue(Pair(false, null))
                            if (t.isEmpty())
                                fetchAuthorsFromApi(it)
                            else {
                                t.add(Author(-1, "", "", ""))
                                authorData.postValue(t)
                            }
                        }

                        override fun onSubscribe(d: Disposable) {
                            mLoading.postValue(
                                Pair(
                                    true,
                                    application.getString(R.string.loading)
                                )
                            )
                        }

                        override fun onError(e: Throwable) {
                            mLoading.postValue(Pair(false, null))
                            mShowMessage.postValue(e.message)
                        }

                    })
            })

    }

}