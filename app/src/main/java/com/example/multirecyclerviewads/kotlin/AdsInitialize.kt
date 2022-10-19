package com.example.multirecyclerviewads.kotlin

import android.content.Context
import com.applovin.sdk.AppLovinSdk
import com.google.android.gms.ads.MobileAds


class AdsInitialize(context: Context) {

    init {
        initSDK(context)
    }

    private fun initSDK(context: Context) {
        MobileAds.initialize(context)
        AppLovinSdk.getInstance(context).mediationProvider = "max"
        AppLovinSdk.initializeSdk(context) { }



    }
}

