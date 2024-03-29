package com.my.myredsoapp.listRedSo

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import com.my.myredsoapp.R
import com.my.myredsoapp.base.BaseActivity

class RedSoActivity : BaseActivity() {
    private val TAG = javaClass.simpleName

    // region BindViews
    @BindView(R.id.viewPager)
    lateinit var mViewPager: ViewPager
    // endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log(TAG, "onCreate")
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_redso
    }

    override fun initView(savedInstanceState: Bundle?) {
        val mPagerAdapter = ViewPagerAdapter(
            supportFragmentManager, resources.getStringArray(R.array.fragment_titles).asList()
        )
        mViewPager.adapter = mPagerAdapter
        mViewPager.currentItem = 0
    }

    override fun onStart() {
        super.onStart()
        log(TAG, "onStart")
    }

    override fun onStop() {
        super.onStop()
        log(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        log(TAG, "onDestroy")
    }

    override fun getEnterTransAnimType(): Int {
        return TRANSITION_ANIM_SLIDE_FROM_RIGHT
    }

    override fun getLeaveTransAnimType(): Int {
        return TRANSITION_ANIM_SLIDE_FROM_RIGHT
    }
}
