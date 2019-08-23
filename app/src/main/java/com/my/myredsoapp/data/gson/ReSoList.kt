package com.my.myredsoapp.data.gson

import com.google.gson.annotations.SerializedName

class ReSoList<T> {

    @SerializedName("results")
    var results : List<T>? = null
}