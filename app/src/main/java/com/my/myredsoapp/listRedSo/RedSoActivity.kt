package com.my.myredsoapp.listRedSo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.my.myredsoapp.R

class RedSoActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName

    // region BindViews
    @BindView(R.id.viewPager)
    lateinit var mViewPager: ViewPager
    // endregion

    private lateinit var mUnBinder : Unbinder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redso)
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
            supportFragmentManager, resources.getStringArray(R.array.fragment_titles).asList()
        )
        mViewPager.adapter = mPagerAdapter
        mViewPager.currentItem = 0
    }

    private fun log(msg : String) {
        Log.d(TAG, msg)
    }
}
