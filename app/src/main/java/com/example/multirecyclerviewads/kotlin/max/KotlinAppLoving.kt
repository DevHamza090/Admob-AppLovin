package com.example.multirecyclerviewads.kotlin.max

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.applovin.sdk.AppLovinSdk
import com.example.multirecyclerviewads.R
import com.example.multirecyclerviewads.databinding.ActivityAppLovinBinding


class KotlinAppLoving : AppCompatActivity() {
    private lateinit var binding: ActivityAppLovinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppLovinBinding.inflate(layoutInflater)
        setContentView(binding.root)


        AppLovinSdk.getInstance(this).mediationProvider = "max"
        AppLovinSdk.initializeSdk(this) { }


        MaxBanner.loadMaxBanner(this, getString(R.string.maxBanner), binding.kotlinMaxAd)




    }
}