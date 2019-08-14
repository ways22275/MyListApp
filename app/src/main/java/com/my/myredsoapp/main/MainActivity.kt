package com.my.myredsoapp.main

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.my.myredsoapp.R
import com.my.myredsoapp.data.entity.RedSoEntity
import com.my.myredsoapp.data.gson.ApiList
import com.my.myredsoapp.data.gson.RedSoGSon
import com.my.myredsoapp.network.ApiBaseResponse
import com.my.myredsoapp.network.ApiClient
import com.my.myredsoapp.network.ApiErrorModel
import com.my.myredsoapp.network.NetworkScheduler
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.kotlin.bindUntilEvent

class MainActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName

    // region BindViews
    @BindView(R.id.viewPager)
    lateinit var mViewPager: ViewPager
    // endregion

    private lateinit var mUnBinder : Unbinder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        log("onCreate")
        init()
    }

    override fun onStart() {
        super.onStart()
        log("onStart")
    }

    override fun onStop() {
        super.onStop()
        log("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
        mUnBinder.unbind()
    }

    private fun init() {
        mUnBinder = ButterKnife.bind(this)
        val mPagerAdapter = ViewPagerAdapter(
            supportFragmentManager, resources.getStringArray(R.array.fragment_titles).asList())
        mViewPager.adapter = mPagerAdapter
        mViewPager.currentItem = 0
    }

    private fun log(msg : String) {
        Log.d(TAG, msg)
    }
}
