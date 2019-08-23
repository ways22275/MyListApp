package com.my.myredsoapp.network

import com.my.myredsoapp.data.gson.ReSoList
import com.my.myredsoapp.data.gson.RedSoItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiRedSoService {

    @GET("/catalog")
    fun getRedSoList(@QueryMap params : Map<String, String>) : Observable<ReSoList<RedSoItem>>
}