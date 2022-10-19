package com.example.multirecyclerviewads

import android.app.Application
import com.example.multirecyclerviewads.kotlin.admob.AppOpenManager

class MyApplication :Application() {


    override fun onCreate() {
        super.onCreate()
        AppOpenManager(this)
    }
}