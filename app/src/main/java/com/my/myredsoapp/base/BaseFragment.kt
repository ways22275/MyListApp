package com.my.myredsoapp.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import butterknife.Unbinder

abstract class BaseFragment : Fragment() {

    companion object{}

    /**
     * 獲取佈局ID
     */
    protected abstract fun getContentViewLayoutID(): Int

    /**
     * 介面初始化
     */
    protected abstract fun initView()

    private lateinit var mUnBinder : Unbinder

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (getContentViewLayoutID() != 0) {
            inflater.inflate(getContentViewLayoutID(), container, false)
        } else {
            super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUnBinder = ButterKnife.bind(this, view)
        initView()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mUnBinder.unbind()
    }

    override fun onDetach() {
        super.onDetach()
    }

    protected fun log (tag : String , msg : String) {
        Log.d(tag, msg)
    }
}