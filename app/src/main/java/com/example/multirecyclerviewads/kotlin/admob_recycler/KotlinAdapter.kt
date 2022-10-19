package com.example.multirecyclerviewads.kotlin.admob_recycler

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.multirecyclerviewads.R
import com.example.multirecyclerviewads.databinding.AdapterItemBinding
import com.example.multirecyclerviewads.databinding.NativeAdContainerBinding
import com.example.multirecyclerviewads.kotlin.admob.AdmobAdaptiveBanner
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import java.text.SimpleDateFormat

class KotlinAdapter(val activity: Activity, val kotlinArrayList: ArrayList<KotlinDataClass>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(activity)
        return if (viewType == ITEM_VIEW) {
            val view: View = layoutInflater.inflate(R.layout.adapter_item, parent, false)
            MainViewHolder(view)
        } else (if (viewType == AD_VIEW) {
            val view: View = layoutInflater.inflate(R.layout.native_ad_container, parent, false)
            AdViewHolder(view)
        } else {
            null
        })!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == ITEM_VIEW) {
            val pos =
                (position - Math.round((position / ITEM_FEED_COUNT).toFloat()))
            (holder as MainViewHolder).bindData(kotlinArrayList[pos])
        } else if (holder.itemViewType == AD_VIEW) {
            (holder as AdViewHolder).bindAdData()
        }
    }

    override fun getItemCount(): Int {
        return if (kotlinArrayList.size > 0) {
            kotlinArrayList.size + Math.round((kotlinArrayList.size / ITEM_FEED_COUNT).toFloat())
        } else kotlinArrayList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if ((position + 1) % ITEM_FEED_COUNT == 0) {
            AD_VIEW
        } else ITEM_VIEW
    }


    private fun populateNativeADView(nativeAd: NativeAd, adView: NativeAdView) {
        // Set the media view.
        adView.mediaView = adView.findViewById(R.id.ad_media)

        // Set other ad assets.
        adView.headlineView = adView.findViewById(R.id.ad_headline)
        adView.bodyView = adView.findViewById(R.id.ad_body)
        adView.callToActionView = adView.findViewById(R.id.ad_call_to_action)
        adView.iconView = adView.findViewById(R.id.ad_app_icon)
        adView.priceView = adView.findViewById(R.id.ad_price)
        adView.starRatingView = adView.findViewById(R.id.ad_stars)
        adView.storeView = adView.findViewById(R.id.ad_store)
        adView.advertiserView = adView.findViewById(R.id.ad_advertiser)

        // The headline and mediaContent are guaranteed to be in every UnifiedNativeAd.
        (adView.headlineView as TextView?)!!.text = nativeAd.headline
        adView.mediaView!!.mediaContent = nativeAd.mediaContent

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.body == null) {
            adView.bodyView!!.visibility = View.INVISIBLE
        } else {
            adView.bodyView!!.visibility = View.VISIBLE
            (adView.bodyView as TextView?)!!.text = nativeAd.body
        }
        if (nativeAd.callToAction == null) {
            adView.callToActionView!!.visibility = View.INVISIBLE
        } else {
            adView.callToActionView!!.visibility = View.VISIBLE
            (adView.callToActionView as Button?)!!.text = nativeAd.callToAction
        }
        if (nativeAd.icon == null) {
            adView.iconView!!.visibility = View.GONE
        } else {
            (adView.iconView as ImageView?)!!.setImageDrawable(
                nativeAd.icon!!.drawable
            )
            adView.iconView!!.visibility = View.VISIBLE
        }
        if (nativeAd.price == null) {
            adView.priceView!!.visibility = View.INVISIBLE
        } else {
            adView.priceView!!.visibility = View.VISIBLE
            (adView.priceView as TextView?)!!.text = nativeAd.price
        }
        if (nativeAd.store == null) {
            adView.storeView!!.visibility = View.INVISIBLE
        } else {
            adView.storeView!!.visibility = View.VISIBLE
            (adView.storeView as TextView?)!!.text = nativeAd.store
        }
        if (nativeAd.starRating == null) {
            adView.starRatingView!!.visibility = View.INVISIBLE
        } else {
            (adView.starRatingView as RatingBar?)!!.rating = nativeAd.starRating!!.toFloat()
            adView.starRatingView!!.visibility = View.VISIBLE
        }
        if (nativeAd.advertiser == null) {
            adView.advertiserView!!.visibility = View.INVISIBLE
        } else {
            (adView.advertiserView as TextView?)!!.text = nativeAd.advertiser
            adView.advertiserView!!.visibility = View.VISIBLE
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad.
        adView.setNativeAd(nativeAd)
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: AdapterItemBinding

        init {
            binding = AdapterItemBinding.bind(itemView)
        }

        fun bindData(kotlinDataClass: KotlinDataClass) {
            binding.tvName.text = kotlinDataClass.name
            val format = SimpleDateFormat("MM/dd/yyyy")
            val date = format.format(kotlinDataClass.date)
            binding.tvDate.text = date
        }
    }

    inner class AdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: NativeAdContainerBinding

        init {
            binding = NativeAdContainerBinding.bind(itemView)
        }

        fun bindAdData() {
            val builder = AdLoader.Builder(activity, activity.getString(R.string.admobNative))
                .forNativeAd { nativeAd: NativeAd? ->
                    val nativeAdView = activity.layoutInflater
                        .inflate(R.layout.full_native_layout, null) as NativeAdView
                    populateNativeADView(nativeAd ?: return@forNativeAd, nativeAdView)
                    binding.adLayout.removeAllViews()
                    binding.adLayout.addView(nativeAdView)
                    Log.d("loadNativeRecyclerView", "onAdLoaded")
                }
            val adLoader = builder.withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    super.onAdFailedToLoad(loadAdError)
                    Toast.makeText(activity, loadAdError.message, Toast.LENGTH_SHORT).show()
                }
            }).build()
            adLoader.loadAd(AdRequest.Builder().build())

//            AdmobAdaptiveBanner(activity,binding.adLayout,activity.getString(R.string.admobBanner))
        }
    }

    companion object {
        const val ITEM_VIEW = 0
        const val AD_VIEW = 1
        const val ITEM_FEED_COUNT = 9
    }
}