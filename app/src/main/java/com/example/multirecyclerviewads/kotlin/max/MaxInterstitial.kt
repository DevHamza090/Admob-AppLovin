package com.example.multirecyclerviewads.kotlin.max

import android.app.Activity
import android.os.Handler
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxInterstitialAd
import java.util.concurrent.TimeUnit


class MaxInterstitial {
    private var maxInterstitialAd: MaxInterstitialAd? = null
    private var retry = 0
    var isAdLoading : Boolean = false



    fun loadMaxInterstitial(activity: Activity?, maxInterstitialId:String){
        if (activity== null){
            return
        }
        if (maxInterstitialAd!= null){
            return
        }
        if (isAdLoading){
            return
        }

        isAdLoading = true



        maxInterstitialAd = MaxInterstitialAd(maxInterstitialId, activity)
        val adListener: MaxAdListener = object : MaxAdListener {
            override fun onAdLoaded(ad: MaxAd) {
                isAdLoading = false

            }
            override fun onAdDisplayed(ad: MaxAd) {}
            override fun onAdHidden(ad: MaxAd) {}
            override fun onAdClicked(ad: MaxAd) {}
            override fun onAdLoadFailed(adUnitId: String, error: MaxError) {
                isAdLoading = false

                retry++
                val delay: Long =
                    TimeUnit.SECONDS.toMillis(Math.pow(2.0, Math.min(6, retry).toDouble()).toLong())
                Handler().postDelayed(Runnable { maxInterstitialAd?.loadAd() }, delay)
            }

            override fun onAdDisplayFailed(ad: MaxAd, error: MaxError) {}
        }
        maxInterstitialAd?.setListener(adListener)
        maxInterstitialAd?.loadAd()
    }

    fun showMaxInterstitial(activity: Activity,maxInterstitialId: String,maxInterstitialListener: MaxShowInterstitialListener){
        if (maxInterstitialAd!!.isReady){
            maxInterstitialAd?.showAd()
            val adListener: MaxAdListener = object : MaxAdListener {
                override fun onAdLoaded(ad: MaxAd) {}
                override fun onAdDisplayed(ad: MaxAd) {}
                override fun onAdHidden(ad: MaxAd) {
                    maxInterstitialListener.onAdDismiss()
                }
                override fun onAdClicked(ad: MaxAd?) {

                }

                override fun onAdLoadFailed(adUnitId: String?, error: MaxError?) {

                }


                override fun onAdDisplayFailed(ad: MaxAd, error: MaxError) {}
            }
            maxInterstitialAd?.setListener(adListener)
            loadMaxInterstitial(activity,maxInterstitialId)

        }else{
            maxInterstitialListener.onAdNotAvailable()
        }
    }
}