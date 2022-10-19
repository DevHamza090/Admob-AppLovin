package com.example.multirecyclerviewads.kotlin.admob.interstitial_ad

interface AdmobInterstitialLoadListeners {
    fun adLoaded()
    fun adFailed()
    fun adAlreadyLoaded()
    fun adIsLoading()

}