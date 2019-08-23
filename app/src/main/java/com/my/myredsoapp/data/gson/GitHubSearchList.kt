package com.my.myredsoapp.data.gson

import com.google.gson.annotations.SerializedName

data class GitHubSearchList<T> (
    @SerializedName("total_count") var totalCount : Int,
    @SerializedName("incomplete_results") var inCompleteResults : Boolean,
    @SerializedName("items") var items : List<T>
)
