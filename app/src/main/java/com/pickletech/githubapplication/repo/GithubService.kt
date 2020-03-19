package com.pickletech.githubapplication.repo

import com.pickletech.githubapplication.model.SearchResult
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("/search/users")
    fun fetchAuthor(@Query("q") query: String): Single<SearchResult>

}