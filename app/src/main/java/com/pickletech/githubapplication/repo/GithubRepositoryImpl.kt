package com.pickletech.githubapplication.repo

import com.pickletech.githubapplication.database.GithubDao
import com.pickletech.githubapplication.di.PerActivity
import com.pickletech.githubapplication.model.Author
import com.pickletech.githubapplication.model.SearchResult
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@PerActivity
class GithubRepositoryImpl @Inject constructor(
    private val githubService: GithubService,
    private val authorDao: GithubDao
) : GithubRepository {


    override fun fetchAuthorFromApi(query: String): Single<SearchResult> {
        return githubService.fetchAuthor(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun fetchAuthor(query: String): Single<MutableList<Author>> {
        return Single.just(query)
            .subscribeOn(Schedulers.io())
            .map {
                authorDao.fetchAuthors("%$it%")
            }
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun saveAuthors(authors: List<Author>) {
        Thread() {
            kotlin.run {
                authorDao.insertAuthors(authors)
            }
        }.start()
    }

}