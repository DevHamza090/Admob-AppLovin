package com.example.multirecyclerviewads.kotlin.max

import android.app.Activity
import android.widget.FrameLayout
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxError
import com.applovin.mediation.nativeAds.MaxNativeAdListener
import com.applovin.mediation.nativeAds.MaxNativeAdLoader
import com.applovin.mediation.nativeAds.MaxNativeAdView


object MaxNative {

    private var nativeAdLoader: MaxNativeAdLoader? = null
    private var nativeAd: MaxAd? = null
    var isLoadingAd :Boolean = false

    fun loadMaxNative(activity: Activity?,maxNativeId : String,maxNativeContainer:FrameLayout){

        if (activity== null){
            return
        }
        if (nativeAd != null){
            return
        }
        if (isLoadingAd){
            return
        }
        isLoadingAd = true

        nativeAdLoader = MaxNativeAdLoader(maxNativeId, activity)
        nativeAdLoader?.setNativeAdListener(object : MaxNativeAdListener() {
            override fun onNativeAdLoaded(nativeAdView: MaxNativeAdView?, ad: MaxAd) {
                // Clean up any pre-existing native ad to prevent memory leaks.
                if (nativeAd != null) {
                    nativeAdLoader?.destroy(nativeAd)
                }
                isLoadingAd = false


                // Save ad for cleanup.
                nativeAd = ad

                // Add ad view to view.
                maxNativeContainer.removeAllViews()
                maxNativeContainer.addView(nativeAdView)
            }

            override fun onNativeAdLoadFailed(adUnitId: String, error: MaxError) {
                // We recommend retrying with exponentially higher delays up to a maximum delay
                isLoadingAd = false

            }

            override fun onNativeAdClicked(ad: MaxAd) {
                // Optional click callback
            }
        })

        nativeAdLoader?.loadAd()
    }
}