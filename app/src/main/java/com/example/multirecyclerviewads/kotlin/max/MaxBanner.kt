package com.example.multirecyclerviewads.kotlin.max

import android.app.Activity
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdViewAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxAdView
import com.example.multirecyclerviewads.BuildConfig
import com.google.android.material.snackbar.Snackbar

object MaxBanner {
    private var maxAdView : MaxAdView ?= null
    var isLoadingAd:Boolean = false

    fun loadMaxBanner(context: Activity?,maxBannerAdId:String, adContainer: LinearLayout) {

        if (context== null){
            return
        }

        if (isLoadingAd){
            return
        }
        if (maxAdView != null){
            return
        }

        isLoadingAd = true


        maxAdView = MaxAdView(maxBannerAdId, context)
        maxAdView?.setListener(object : MaxAdViewAdListener {
            override fun onAdExpanded(ad: MaxAd) {}
            override fun onAdCollapsed(ad: MaxAd) {}
            override fun onAdLoaded(ad: MaxAd) {
                adContainer.visibility = View.VISIBLE
                isLoadingAd = false
            }
            override fun onAdDisplayed(ad: MaxAd) {}
            override fun onAdHidden(ad: MaxAd) {}
            override fun onAdClicked(ad: MaxAd) {}
            override fun onAdLoadFailed(adUnitId: String, error: MaxError) {
                isLoadingAd = false
                adContainer.visibility = View.GONE
                if (BuildConfig.DEBUG) {
                    Snackbar.make(
                        context.window.decorView.rootView,
                        error.message,
                        Snackbar.LENGTH_LONG
                    ).show()

                }
            }
            override fun onAdDisplayFailed(ad: MaxAd, error: MaxError) {}
        })
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        maxAdView?.layoutParams = FrameLayout.LayoutParams(width, height, Gravity.BOTTOM)
        maxAdView?.setBackgroundColor(Color.WHITE)
        adContainer.addView(maxAdView)
        maxAdView?.loadAd()
    }
}