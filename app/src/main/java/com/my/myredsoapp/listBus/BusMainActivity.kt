package com.my.myredsoapp.listBus

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import com.my.myredsoapp.base.BaseActivity
import java.security.SignatureException
import java.text.SimpleDateFormat
import android.util.Base64
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


class BusMainActivity : BaseActivity() {
    private val TAG = javaClass.simpleName

    // region BindViews
    private lateinit var mButtonSign : AppCompatButton
    private lateinit var mProgress : ProgressBar
    // endregion

    override fun getContentViewLayoutID(): Int {
        return com.my.myredsoapp.R.layout.activity_bus
    }

    override fun initView(savedInstanceState: Bundle?) {
        mProgress = findViewById(com.my.myredsoapp.R.id.progressBar_sign_ticket)
        mButtonSign = findViewById(com.my.myredsoapp.R.id.button_sign_ticket)

        mButtonSign.setOnClickListener {
            buildTicket()
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

    override fun getEnterTransAnimType(): Int {
        return TRANSITION_ANIM_SLIDE_FROM_BOTTOM
    }

    override fun getLeaveTransAnimType(): Int {
        return TRANSITION_ANIM_SLIDE_FROM_BOTTOM
    }

    private fun buildTicket() {
        mProgress.visibility = View.VISIBLE

        // 申請的APPID
        val appID = "786d6371606c49dea8e77667c7bbf243"
        // 申請的APPKey
        val appKey = "9KY2kjRCtqmyqnFG8NJq6XsJ17I"
        // 取得當下的GMT時間
        val xDate = getServerTime()
        log(TAG, "xDate : $xDate")
        val signDate = "x-date: $xDate"
        var signature = ""

        try {
            // 取得加密簽章
            signature = signature(signDate, appKey)
        } catch (e1: SignatureException) {
            // TODO Auto-generated catch block
            e1.printStackTrace()
        }

        val sAuth = "hmac username=\"$appID\", algorithm=\"hmac-sha1\", headers=\"x-date\", signature=\"$signature\""
        log(TAG, "final signature : $sAuth")
        mProgress.visibility = View.GONE
    }

    @Throws(java.security.SignatureException::class)
    fun signature(xData: String, AppKey: String): String {
        try {
            // get an hmac_sha1 key from the raw key bytes
            val signingKey = SecretKeySpec(AppKey.toByteArray(), "HmacSHA1")

            // get an hmac_sha1 Mac instance and initialize with the signing key
            val mac = Mac.getInstance("HmacSHA1")
            mac.init(signingKey)

            // compute the hmac on input data bytes
            val rawHMac = mac.doFinal(xData.toByteArray())
            return Base64.encodeToString(rawHMac, Base64.DEFAULT).trim()

        } catch (e: Exception) {
            throw SignatureException("Failed to generate HMac : " + e.message)
        }

    }

    private fun getServerTime(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat(
            "EEE, dd MMM yyyy HH:mm:ss ", Locale.US)
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")
        return dateFormat.format(calendar.time) + "GMT"
    }
}