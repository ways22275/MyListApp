package com.my.myredsoapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.my.myredsoapp.R

open class BaseActivity : AppCompatActivity() {

    companion object {
        // for overridePendingTransition
        const val TRANSITION_ANIM_DEFAULT = 0
        const val TRANSITION_ANIM_SLIDE_FROM_RIGHT = 1
        const val TRANSITION_ANIM_SLIDE_FROM_LEFT = 2
        const val TRANSITION_ANIM_SLIDE_FROM_BOTTOM = 3
        const val TRANSITION_ANIM_FADE = 4
    }

    // TODO fix Warning
    private var mEnterTransAnimType = getEnterTransAnimType()
    private var mLeaveTransAnimType = getLeaveTransAnimType()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overrideEnterTransAnim()
    }

    override fun onPause() {
        super.onPause()
        if (isFinishing)
            overrideLeaveTransAnim()
    }

    protected open fun getEnterTransAnimType() : Int {
        return TRANSITION_ANIM_DEFAULT
    }

    protected open fun getLeaveTransAnimType() : Int {
        return TRANSITION_ANIM_DEFAULT
    }

    private fun overrideEnterTransAnim () {
        var enterType = TRANSITION_ANIM_DEFAULT
        var exitType = TRANSITION_ANIM_DEFAULT

        when (mEnterTransAnimType) {
            TRANSITION_ANIM_SLIDE_FROM_RIGHT -> {
                enterType = R.anim.slide_in_right
                exitType = R.anim.stay
            }
            TRANSITION_ANIM_SLIDE_FROM_LEFT -> {
                enterType = R.anim.slide_in_left
                exitType = R.anim.stay
            }
            TRANSITION_ANIM_SLIDE_FROM_BOTTOM -> {
                enterType = R.anim.slide_in_bottom
                exitType = R.anim.stay
            }
            TRANSITION_ANIM_FADE -> {
                enterType = R.anim.fade_in
                exitType = R.anim.fade_out
            }
        }
        overridePendingTransition(enterType, exitType)
    }

    private fun overrideLeaveTransAnim () {
        var leaveType = TRANSITION_ANIM_DEFAULT
        var exitType = TRANSITION_ANIM_DEFAULT

        when (mLeaveTransAnimType) {
            TRANSITION_ANIM_SLIDE_FROM_RIGHT -> {
                leaveType = R.anim.stay
                exitType = R.anim.slide_out_right
            }
            TRANSITION_ANIM_SLIDE_FROM_LEFT -> {
                leaveType = R.anim.stay
                exitType = R.anim.slide_out_left
            }
            TRANSITION_ANIM_SLIDE_FROM_BOTTOM -> {
                leaveType = R.anim.stay
                exitType = R.anim.slide_out_bottom
            }
            TRANSITION_ANIM_FADE -> {
                leaveType = R.anim.fade_out
                exitType = R.anim.fade_in
            }
        }
        overridePendingTransition(leaveType, exitType)
    }
}