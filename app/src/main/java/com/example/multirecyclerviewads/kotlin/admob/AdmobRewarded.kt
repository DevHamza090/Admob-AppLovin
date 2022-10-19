package com.example.multirecyclerviewads.kotlin.admob

import android.app.Activity
import android.os.CountDownTimer
import android.util.Log
import com.example.multirecyclerviewads.R
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

object AdmobRewarded {
    private var mCoinCount: Int = 0
    private var mIsLoading = false
    private var mRewardedAd: RewardedAd? = null

    val TAG = "AdmobRewarded"

     fun loadRewardedAd(activity: Activity,admobRewardedId:String) {

        if (mRewardedAd == null) {
            mIsLoading = true
            var adRequest = AdRequest.Builder().build()

            RewardedAd.load(
                activity,
                admobRewardedId,
                adRequest,
                object : RewardedAdLoadCallback() {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        Log.d(TAG, adError.message)
                        mIsLoading = false
                        mRewardedAd = null
                    }

                    override fun onAdLoaded(rewardedAd: RewardedAd) {
                        Log.d(TAG, "Ad was loaded.")
                        mRewardedAd = rewardedAd
                        mIsLoading = false
                    }
                }
            )
        }
    }


     fun showRewardedVideo(activity: Activity) {

        if (mRewardedAd != null) {
            mRewardedAd?.fullScreenContentCallback =
                object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        Log.d(TAG, "Ad was dismissed.")
                        // Don't forget to set the ad reference to null so you
                        // don't show the ad a second time.
                        mRewardedAd = null
                        loadRewardedAd(activity,activity.getString(R.string.admobRewarded))
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        Log.d(TAG, "Ad failed to show.")
                        // Don't forget to set the ad reference to null so you
                        // don't show the ad a second time.
                        mRewardedAd = null
                    }

                    override fun onAdShowedFullScreenContent() {
                        Log.d(TAG, "Ad showed fullscreen content.")
                        // Called when ad is dismissed.
                    }
                }

            mRewardedAd?.show(
                activity,
                OnUserEarnedRewardListener() {
                    fun onUserEarnedReward(rewardItem: RewardItem) {
                        var rewardAmount = rewardItem.amount
                        addCoins(rewardAmount)
                        Log.d("TAG", "User earned the reward.")
                    }
                }
            )
        }
    }

    private fun addCoins(coins: Int) {
        mCoinCount += coins
//        coin_count_text.text = "Coins: $mCoinCount"
    }



}