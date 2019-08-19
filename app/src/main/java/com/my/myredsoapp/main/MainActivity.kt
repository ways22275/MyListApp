package com.my.myredsoapp.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import butterknife.BindView
import butterknife.OnClick
import butterknife.Unbinder
import com.my.myredsoapp.R
import com.my.myredsoapp.listRedSo.RedSoActivity

class MainActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName

    // region BindViews
    lateinit var mButtonTest : AppCompatButton
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
