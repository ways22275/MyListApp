package com.my.myredsoapp.network

import com.my.myredsoapp.data.gson.ApiList
import com.my.myredsoapp.data.gson.RedSoGSon
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("/catalog")
    fun getRedSoList(@QueryMap params : Map<String, String>): Observable<ApiList<RedSoGSon>>
}