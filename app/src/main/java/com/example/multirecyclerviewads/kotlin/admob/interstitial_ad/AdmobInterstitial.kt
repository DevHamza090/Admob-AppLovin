package com.example.multirecyclerviewads.kotlin.admob.interstitial_ad

import android.app.Activity
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.material.snackbar.Snackbar

object AdmobInterstitial {

    private var mInterstitialAd: InterstitialAd? = null
    private var isLoadingAd: Boolean = false
    val LOG = "FullScreenAds"


    fun loadAd(context: Activity?, adID: String, listenerFullAd: AdmobInterstitialLoadListeners) {
        if (context == null) return
        if (isLoadingAd) {
            listenerFullAd.adIsLoading()
            return
        }
        if (mInterstitialAd != null) {
            listenerFullAd.adAlreadyLoaded()
            return
        }

        isLoadingAd = true
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            context,
            adID,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(LOG, "Error MSG : ${adError.message}")
                    Log.d(LOG, "Error INFO : ${adError.responseInfo}")
                    Log.d(LOG, "Error CAUSE : ${adError.cause}")
                    Log.d(LOG, "Error CODE : ${adError.code}")
                    mInterstitialAd = null
                    isLoadingAd = false
                    listenerFullAd.adFailed()
                    if (isDebug()) {
                        Snackbar.make(
                            context.window.decorView.rootView,
                            adError.message,
                            Snackbar.LENGTH_LONG
                        ).show()

                    }

                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(LOG, "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                    isLoadingAd = false
                    listenerFullAd.adLoaded()

                }
            })
    }


    fun showAndLoad(
        context: Activity?,
        adID: String,
        listenerFullAd: AdmobInterstitialLoadListeners,
        listenersShowAd: AdmobInterstitialShowListeners
    ) {
        if (context == null) return
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(context)
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d(LOG, "Ad was dismissed.")
                    listenersShowAd.adDismiss()
                    if (adID.length == 38) {
                        loadAd(context, adID, listenerFullAd)
                    }
                }

                override fun onAdClicked() {
                    Log.d(LOG, "Ad was onAdClicked.")
                    super.onAdClicked()
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    Log.d(LOG, "Ad was onAdFailedToShowFullScreenContent code ${p0.code}.")
                    Log.d(LOG, "Ad was onAdFailedToShowFullScreenContent cause ${p0.cause}.")
                    Log.d(LOG, "Ad was onAdFailedToShowFullScreenContent message ${p0.message}.")

                    super.onAdFailedToShowFullScreenContent(p0)
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d(LOG, "Ad showed fullscreen content.")
                    mInterstitialAd = null
                }
            }


        } else {
            listenersShowAd.adNotAvailable()
        }


    }


    fun isDebug(): Boolean {
        return false
    }

}