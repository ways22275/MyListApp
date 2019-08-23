package com.my.myredsoapp.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import butterknife.OnClick
import com.my.myredsoapp.R
import com.my.myredsoapp.base.BaseActivity
import com.my.myredsoapp.listRedSo.RedSoActivity
import com.my.myredsoapp.util.Constant

class MainActivity : BaseActivity() {
    private val TAG = javaClass.simpleName

    // region BindViews
    private lateinit var mButtonTest : AppCompatButton
    // endregion

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

    private fun init() {
        mButtonTest = findViewById(R.id.button_test)
        mButtonTest.setOnClickListener{
            goToRedSoList()
        }
    }

    private fun goToRedSoList() {
        val intent = Intent()
        intent.setClass(this, RedSoActivity::class.java)
        startActivity(intent)
    }

    private fun log(msg : String) {
        Log.d(TAG, msg)
    }
}
