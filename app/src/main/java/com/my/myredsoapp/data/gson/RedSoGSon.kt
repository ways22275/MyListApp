package com.my.myredsoapp.data.gson

import com.google.gson.annotations.SerializedName
import com.my.myredsoapp.data.entity.RedSoEntity

data class RedSoGSon (
    @SerializedName("id")           var id : String,
    @SerializedName("type")         var type : String,
    @SerializedName("name")         var name : String,
    @SerializedName("position")     var position : String,
    @SerializedName("expertise")    var expertise : List<String>,
    @SerializedName("avatar")       var avatar : String,
    @SerializedName("url")          var url : String)
{
    fun toEntity() : RedSoEntity {
        val entity = RedSoEntity(type)
        entity.id = this.id
        entity.name = this.name
        entity.position = this.position
        entity.expertise = this.expertise
        entity.avatar = this.avatar
        entity.url = this.url
        return entity
    }
}


