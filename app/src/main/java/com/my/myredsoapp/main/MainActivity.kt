package com.my.myredsoapp.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import butterknife.BindView
import butterknife.OnClick
import com.my.myredsoapp.R
import com.my.myredsoapp.base.BaseActivity
import com.my.myredsoapp.listBus.BusMainActivity
import com.my.myredsoapp.listRedSo.RedSoActivity

class MainActivity : BaseActivity() {
    private val TAG = javaClass.simpleName

    // region BindViews
    @BindView(R.id.button_to_red_so)
    lateinit var mButtonRedSo : AppCompatButton
    @BindView(R.id.button_to_bus_list)
    lateinit var mButtonBus : AppCompatButton
    // endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log(TAG, "onCreate")
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        mButtonRedSo.setOnClickListener{
            goToRedSoList()
        }
        mButtonBus.setOnClickListener{
            goToBusList()
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

    @OnClick(R.id.button_to_red_so)
    fun onButtonTestClicked(view : View) {

    }

    private fun goToRedSoList() {
        val intent = Intent()
        intent.setClass(this, RedSoActivity::class.java)
        startActivity(intent)
    }

    private fun goToBusList() {
        val intent = Intent()
        intent.setClass(this, BusMainActivity::class.java)
        startActivity(intent)
    }
}
