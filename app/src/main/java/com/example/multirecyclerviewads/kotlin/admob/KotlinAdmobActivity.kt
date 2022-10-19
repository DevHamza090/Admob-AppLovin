package com.example.multirecyclerviewads.kotlin.admob

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.multirecyclerviewads.R
import com.example.multirecyclerviewads.kotlin.AdsInitialize
import com.example.multirecyclerviewads.kotlin.admob.interstitial_ad.AdmobInterstitial
import com.example.multirecyclerviewads.kotlin.admob.interstitial_ad.AdmobInterstitialLoadListeners
import com.example.multirecyclerviewads.kotlin.admob.interstitial_ad.AdmobInterstitialShowListeners
import com.example.multirecyclerviewads.kotlin.admob.native_ad.NativeAds
import com.example.multirecyclerviewads.kotlin.admob.native_ad.NativeListener
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView

class KotlinAdmobActivity : AppCompatActivity(), AdmobInterstitialLoadListeners {
    private var ads: AdsInitialize? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_admob)

        AdsInitialize(this)

        AdmobInterstitial.loadAd(this, getString(R.string.admobInterstitial), this)

        AdmobRewarded.loadRewardedAd(this,getString(R.string.admobRewarded))

        val bannerContainer = findViewById<LinearLayout>(R.id.admobBannerContainer)
        AdmobAdaptiveBanner(this,bannerContainer,getString(R.string.admobBanner))


        val fram = findViewById<FrameLayout>(R.id.nativeAdContainer)

        NativeAds.loadAd(this,  getString(R.string.admobNative), object : NativeListener {
            override fun adNativeLoaded(currentNativeAd: NativeAd) {
                fram.visibility = View.VISIBLE
                fram.visibility = View.VISIBLE
                val layoutInflater =
                    layoutInflater.inflate(R.layout.ad_unified, null) as NativeAdView
                NativeAds.populateNativeAdView(currentNativeAd, layoutInflater)
                fram.removeAllViews()
                fram.addView(layoutInflater)
                Log.d("NativeAds","adNativeLoaded")

            }

            override fun adNativeFailed() {
                fram.visibility = View.GONE
                Log.d("NativeAds","adNativeFailed")


            }


            override fun adNativeIsLoading() {
                Log.d("NativeAds","adNativeIsLoading")


            }

            override fun onNativeValidate() {
                fram.visibility = View.GONE
                Log.d("NativeAds","onNativeValidate")


            }

        })

        findViewById<Button>(R.id.showADs).setOnClickListener {
            AdmobInterstitial.showAndLoad(
                this,
                "ca-app-pub-3940256099942544/1033173712",
                this,
                object : AdmobInterstitialShowListeners {
                    override fun adDismiss() {
                        Log.d("fullscreenAds", "closed")

                    }

                    override fun adNotAvailable() {
                        Log.d("fullscreenAds", "sorry")

                    }

                })

        }

        findViewById<Button>(R.id.showRewarded).setOnClickListener {
            AdmobRewarded.showRewardedVideo(this)

        }
/*
        findViewById<TextView>(R.id.loadAds).setOnClickListener {
            NativeAds.loadAd(this, getString(R.string.admobNative), object : NativeListener {
                override fun adNativeLoaded(currentNativeAd: NativeAd) {
                    fram.visibility = View.VISIBLE
                    fram.visibility = View.VISIBLE
                    val layoutInflater =
                        layoutInflater.inflate(R.layout.ad_unified, null) as NativeAdView
                    NativeAds.populateNativeAdView(currentNativeAd, layoutInflater)
                    fram.removeAllViews()
                    fram.addView(layoutInflater)
                    Log.d("NativeAds","adNativeLoaded")

                }

                override fun adNativeFailed() {
                    fram.visibility = View.GONE
                    Log.d("NativeAds","adNativeFailed")


                }


                override fun adNativeIsLoading() {
                    Log.d("NativeAds","adNativeIsLoading")


                }

                override fun onNativeValidate() {
                    fram.visibility = View.GONE
                    Log.d("NativeAds","onNativeValidate")


                }

            })


        }
*/

    }

    override fun adLoaded() {
        Log.d("fullscreenAds", "adLoaded")


    }

    override fun adFailed() {
        Log.d("fullscreenAds", "adFailed")


    }

    override fun adAlreadyLoaded() {
        Log.d("fullscreenAds", "adAlreadyLoaded")


    }

    override fun adIsLoading() {
        Log.d("fullscreenAds", "adIsLoading")


    }
}