package com.pickletech.githubapplication.viewmodel

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.pickletech.githubapplication.GithubApplication
import com.pickletech.githubapplication.R
import com.pickletech.githubapplication.model.Author
import com.pickletech.githubapplication.model.SearchResult
import com.pickletech.githubapplication.repo.GithubRepository
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class AuthorListViewModel
@Inject constructor(
    var application: GithubApplication,
    var repository: GithubRepository
) : BaseViewModel(application) {

    val searchKey: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val authorData: MutableLiveData<Pair<String, List<Author>>> by lazy {
        MutableLiveData<Pair<String, List<Author>>>()
    }

    fun fetchAuthors(key: String?) {

        val query = (if (!TextUtils.isEmpty(key)) key else "alphabetagama")!!

        if (query.contentEquals(authorData.value?.first ?: ""))
            return


        repository.fetchAuthor(query)
            .subscribe(object : SingleObserver<MutableList<Author>> {
                override fun onSuccess(t: MutableList<Author>) {
                    mLoading.postValue(Pair(false, null))
                    if (t.isEmpty())
                        fetchAuthorsFromApi(query)
                    else {
                        t.add(Author(-1, "", "", ""))
                        authorData.postValue(Pair(query, t))
                    }
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

    fun fetchAuthorsFromApi(query: String) {

        repository.fetchAuthorFromApi(query)
            .subscribe(object : SingleObserver<SearchResult> {
                override fun onSuccess(t: SearchResult) {
                    mLoading.postValue(Pair(false, null))
                    authorData.postValue(Pair(query, t.items))

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
}