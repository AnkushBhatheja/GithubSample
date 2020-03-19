package com.pickletech.githubapplication.repo

import com.pickletech.githubapplication.model.Author
import com.pickletech.githubapplication.model.SearchResult
import io.reactivex.Single

interface GithubRepository {
    fun fetchAuthorFromApi(query: String): Single<SearchResult>

    fun fetchAuthor(query: String): Single<MutableList<Author>>

    fun saveAuthors(authors: List<Author>)
}