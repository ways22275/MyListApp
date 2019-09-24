package com.my.myredsoapp.listGitHubUser

import android.os.Bundle
import com.my.myredsoapp.R
import com.my.myredsoapp.base.BaseActivity

class GitHubSearchActivity : BaseActivity() {

    private val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log(TAG, "onCreate")
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_github_search
    }

    override fun initView(savedInstanceState: Bundle?) {
        // TODO init
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