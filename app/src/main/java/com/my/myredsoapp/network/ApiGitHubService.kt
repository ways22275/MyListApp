package com.my.myredsoapp.network

import com.my.myredsoapp.data.gson.GitHubSearchList
import com.my.myredsoapp.data.gson.GitHubSearchUserItem
import com.my.myredsoapp.data.gson.ReSoList
import com.my.myredsoapp.data.gson.RedSoItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiGitHubService {

    @GET("/search/users")
    fun searchUsers(@QueryMap params : Map<String, String>) : Observable<GitHubSearchList<GitHubSearchUserItem>>

}