package com.my.myredsoapp.data.gson

import com.google.gson.annotations.SerializedName

class ApiList<T> {

    @SerializedName("results")
    var results : List<T>? = null
}