package com.pickletech.githubapplication.repo

import com.pickletech.githubapplication.database.GithubDao
import com.pickletech.githubapplication.model.Author
import com.pickletech.githubapplication.model.SearchResult
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.Callable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepositoryImpl @Inject constructor(
    private val githubService: GithubService,
    private val authorDao: GithubDao
) : GithubRepository {

    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    override fun fetchAuthorFromApi(query: String): Single<SearchResult> {
        return githubService.fetchAuthor(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun fetchAuthor(query: String): Single<MutableList<Author>> {

        return Single.just(query)
            .subscribeOn(Schedulers.io())
            .map {
                authorDao.fetchAuthors(it)
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