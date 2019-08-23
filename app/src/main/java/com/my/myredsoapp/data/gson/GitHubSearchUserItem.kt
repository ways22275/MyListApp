package com.my.myredsoapp.data.gson

import com.google.gson.annotations.SerializedName
import com.my.myredsoapp.data.entity.GitHubUserEntity

data class GitHubSearchUserItem (
    @SerializedName("id") var id : Int,
    @SerializedName("login") var loginName : String,
    @SerializedName("avatar_url") var avatarUrl : String,
    @SerializedName("html_url") var indexUrl : String
) {
    fun toEntity() : GitHubUserEntity {
        val entity  = GitHubUserEntity(id)
        entity.loginName = loginName
        entity.avatarUrl = avatarUrl
        entity.indexUrl = indexUrl
        return entity
    }
}