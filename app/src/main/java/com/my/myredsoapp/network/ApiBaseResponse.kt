package com.my.myredsoapp.network

import android.content.Context
import com.google.gson.Gson
import com.my.myredsoapp.widget.LoadingDialog
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class ApiBaseResponse<T> (private val context: Context): Observer<T> {

    abstract fun onSuccess(dataList: T)
    abstract fun onFailure(status_code : Int , apiErrorModel: ApiErrorModel)

    override fun onSubscribe(d: Disposable) {
        LoadingDialog.show(context)
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onComplete() {
        LoadingDialog.cancel()
    }

    override fun onError(e: Throwable) {
        LoadingDialog.cancel()
        // 連接server成功但返回錯誤代碼
        if (e is HttpException) {
            val apiErrorModel: ApiErrorModel = when (e.code()) {
                ApiErrorType.INTERNAL_SERVER_ERROR.code ->
                    ApiErrorType.INTERNAL_SERVER_ERROR.getApiErrorModel(context)
                ApiErrorType.BAD_GATEWAY.code ->
                    ApiErrorType.BAD_GATEWAY.getApiErrorModel(context)
                ApiErrorType.NOT_FOUND.code ->
                    ApiErrorType.NOT_FOUND.getApiErrorModel(context)
                else -> otherError(e)
            }
            onFailure(e.code(), apiErrorModel)
            return
        }
        // 發送網路or其他未知問題
        val apiErrorType: ApiErrorType = when (e) {
            is UnknownHostException -> ApiErrorType.NETWORK_NOT_CONNECT
            is ConnectException -> ApiErrorType.NETWORK_NOT_CONNECT
            is SocketTimeoutException -> ApiErrorType.CONNECTION_TIMEOUT
            else -> ApiErrorType.UNEXPECTED_ERROR
        }
        onFailure(apiErrorType.code, apiErrorType.getApiErrorModel(context))
    }

    private fun otherError(e: HttpException) =
        Gson().fromJson(e.response().errorBody()?.charStream(), ApiErrorModel::class.java)
}