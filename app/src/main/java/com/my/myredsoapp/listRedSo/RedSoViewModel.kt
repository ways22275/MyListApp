package com.my.myredsoapp.listRedSo

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.my.myredsoapp.data.entity.RedSoEntity
import com.my.myredsoapp.data.gson.ApiList
import com.my.myredsoapp.data.gson.RedSoGSon
import com.my.myredsoapp.network.ApiBaseResponse
import com.my.myredsoapp.network.ApiClient
import com.my.myredsoapp.network.ApiErrorModel
import com.my.myredsoapp.network.NetworkScheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class RedSoViewModel : ViewModel() {

    var mRedSoList: MutableLiveData<List<RedSoEntity>> = MutableLiveData()
        private set
    var mCompositeDisposable : CompositeDisposable = CompositeDisposable()
        private set
    var page = 0
        private set

    fun loadDataList(context : Context, team : String) {
        page++
        val params = HashMap<String, String>()
        params["team"] = team.toLowerCase()
        params["page"] = page.toString()

        ApiClient.instance.mService
            .getRedSoList(params)
            .compose(NetworkScheduler.compose())
            .subscribe(object : ApiBaseResponse<ApiList<RedSoGSon>>(context){
                override fun onSubscribe(d: Disposable) {
                    super.onSubscribe(d)
                    mCompositeDisposable.add(d)
                }

                override fun onSuccess(dataList: ApiList<RedSoGSon>) {
                    val entityList = arrayListOf<RedSoEntity>()
                    for (data in dataList.results!!) {
                        entityList.add(data.toEntity())
                    }
                    mRedSoList.value = entityList
                }

                override fun onFailure(status_code: Int, apiErrorModel: ApiErrorModel) {
                    // TODO
                }
            })
    }

    fun resetPageCount() {
        page = 0
    }

    fun unregisterLiveData(lifecycleOwner : LifecycleOwner) {
        mCompositeDisposable.clear()
        mRedSoList.removeObservers(lifecycleOwner)
    }
}