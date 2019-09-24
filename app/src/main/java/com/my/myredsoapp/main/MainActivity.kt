package com.my.myredsoapp.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import butterknife.BindView
import butterknife.OnClick
import com.my.myredsoapp.R
import com.my.myredsoapp.base.BaseActivity
import com.my.myredsoapp.listRedSo.RedSoActivity

class MainActivity : BaseActivity() {
    private val TAG = javaClass.simpleName

    // region BindViews
    @BindView(R.id.button_test)
    lateinit var mButtonTest : AppCompatButton
    // endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log(TAG, "onCreate")
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        mButtonTest.setOnClickListener{
            goToRedSoList()
        }
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

    override fun getEnterTransAnimType() : Int {
        return TRANSITION_ANIM_SLIDE_FROM_BOTTOM
    }

    override fun getLeaveTransAnimType(): Int {
        return TRANSITION_ANIM_SLIDE_FROM_BOTTOM
    }

    @OnClick(R.id.button_test)
    fun onButtonTestClicked(view : View) {

    }

    private fun goToRedSoList() {
        val intent = Intent()
        intent.setClass(this, RedSoActivity::class.java)
        startActivity(intent)
    }
}
