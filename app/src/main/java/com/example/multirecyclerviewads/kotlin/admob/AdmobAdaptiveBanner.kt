package com.example.multirecyclerviewads.kotlin.admob

import android.app.Activity
import android.util.DisplayMetrics
import android.util.Log
import android.widget.LinearLayout
import com.google.android.gms.ads.*

class AdmobAdaptiveBanner(val activity: Activity,val ad_view_container:LinearLayout,val admobBannerId:String) {
    var adView: AdView?= null
    private var initialLayoutComplete = false
    val TAG = "AdaptiveBanner"

    private val adSize: AdSize
        get() {
            val display = activity.windowManager.defaultDisplay
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)

            val density = outMetrics.density

            var adWidthPixels = ad_view_container.width.toFloat()
            if (adWidthPixels == 0f) {
                adWidthPixels = outMetrics.widthPixels.toFloat()
            }

            val adWidth = (adWidthPixels / density).toInt()
            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth)
        }

    init {
        ad_view_container.viewTreeObserver.addOnGlobalLayoutListener {
            if (!initialLayoutComplete) {
                initialLayoutComplete = true
                loadBanner()
            }
        }
    }

    private fun loadBanner() {
        adView = AdView(activity)
        adView?.adUnitId = admobBannerId

        adView?.setAdSize(adSize)

        // Create an ad request.
        val adRequest = AdRequest.Builder().build()

        // Start loading the ad in the background.
        ad_view_container.addView(adView)
        adView?.loadAd(adRequest)
        adView?.adListener  = object :AdListener(){

            override fun onAdLoaded() {
                super.onAdLoaded()
                Log.d(TAG,"onAdLoaded()")
            }

            override fun onAdFailedToLoad(p0: LoadAdError) {
                super.onAdFailedToLoad(p0)
                Log.d(TAG,p0.message)
            }

        }
    }


}