package com.example.multirecyclerviewads.kotlin.admob.native_ad

import com.google.android.gms.ads.nativead.NativeAd

interface NativeListener {
    fun adNativeLoaded(currentNativeAd: NativeAd)
    fun adNativeFailed()
    fun adNativeIsLoading()
    fun onNativeValidate()
}