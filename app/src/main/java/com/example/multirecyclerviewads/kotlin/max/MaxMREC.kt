package com.example.multirecyclerviewads.kotlin.max

import android.R
import android.app.Activity
import android.graphics.Color
import android.view.Gravity
import android.widget.FrameLayout
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdFormat
import com.applovin.mediation.MaxAdViewAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxAdView
import com.applovin.sdk.AppLovinSdkUtils


object MaxMREC {


    private var MRECAdview: MaxAdView? = null
    var isLoadingAd : Boolean = false


    fun createMREC(activity: Activity?,mrecId:String,MRECLayout:FrameLayout){

        if (activity == null){
            return
        }
        if (MRECAdview!= null){
            return
        }
        if (isLoadingAd){
            return
        }

        isLoadingAd = true

        MRECAdview = MaxAdView(mrecId, MaxAdFormat.MREC, activity)
        MRECAdview?.setListener(object : MaxAdViewAdListener {
            override fun onAdExpanded(ad: MaxAd) {}
            override fun onAdCollapsed(ad: MaxAd) {}
            override fun onAdLoaded(ad: MaxAd) {
                isLoadingAd = false
            }
            override fun onAdDisplayed(ad: MaxAd) {}
            override fun onAdHidden(ad: MaxAd) {}
            override fun onAdClicked(ad: MaxAd) {}
            override fun onAdLoadFailed(adUnitId: String, error: MaxError) {
                isLoadingAd = false
            }
            override fun onAdDisplayFailed(ad: MaxAd, error: MaxError) {}
        })
        val width = AppLovinSdkUtils.dpToPx(activity, 300)
        val height = AppLovinSdkUtils.dpToPx(activity, 250)
        MRECAdview?.layoutParams = FrameLayout.LayoutParams(width, height, Gravity.CENTER)
        MRECAdview?.setBackgroundColor(Color.WHITE)

        MRECLayout.addView(MRECAdview)
        MRECAdview?.loadAd()
        MRECAdview?.startAutoRefresh()
    }


}