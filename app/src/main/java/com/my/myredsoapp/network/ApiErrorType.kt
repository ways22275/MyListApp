package com.my.myredsoapp.network

import android.content.Context
import androidx.annotation.StringRes
import com.my.myredsoapp.R

enum class ApiErrorType (val code: Int, @param:StringRes private val msgID: Int) {

    INTERNAL_SERVER_ERROR(500, R.string.service_error),
    BAD_GATEWAY(502, R.string.service_error),
    NOT_FOUND(404, R.string.service_not_found),
    CONNECTION_TIMEOUT(408, R.string.service_timeout),
    NETWORK_NOT_CONNECT(499, R.string.network_wrong),
    UNEXPECTED_ERROR(700, R.string.unexpected_error);

    private val DEFAULT_CODE = 1
    fun getApiErrorModel(context: Context): ApiErrorModel {
        return ApiErrorModel(DEFAULT_CODE, context.getString(msgID))
    }
}