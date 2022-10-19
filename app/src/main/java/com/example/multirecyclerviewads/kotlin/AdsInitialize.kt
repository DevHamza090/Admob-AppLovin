package com.example.multirecyclerviewads.kotlin

import android.app.Activity
import android.content.Context
import android.widget.LinearLayout
import com.applovin.sdk.AppLovinSdk
import com.facebook.ads.AdSettings
import com.facebook.ads.AudienceNetworkAds
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.processNextEventInCurrentThread


class AdsInitialize(context: Context) {

    init {
        initSDK(context)
    }

    private fun initSDK(context: Context) {
        //If you do not want to enable Limited Data Use (LDU) mode, pass SetDataProcessingOptions() an empty string array:
         /*Use*/     AdSettings.setDataProcessingOptions( arrayOf<String>() )


        //To enable LDU for users and specify user geography, call SetDataProcessingOptions() in a form like this:
        /*Use*/ AdSettings.setDataProcessingOptions( arrayOf("LDU"), 1, 1000 )



        MobileAds.initialize(context)
        AudienceNetworkAds.initialize(context)
        AppLovinSdk.getInstance(context).mediationProvider = "max"
        AppLovinSdk.initializeSdk(context){}



    }
}

