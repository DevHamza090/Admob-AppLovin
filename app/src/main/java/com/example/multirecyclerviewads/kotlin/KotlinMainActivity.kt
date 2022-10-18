package com.example.multirecyclerviewads.kotlin

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.multirecyclerviewads.databinding.ActivityKotlinMainBinding
import java.util.*

class KotlinMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKotlinMainBinding
    private var kotlinAdapter: KotlinAdapter? = null
    private var kotlinArrayList: ArrayList<KotlinDataClass>? = null
    private var rvKotlinMain : RecyclerView?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKotlinMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        kotlinArrayList = ArrayList()

        kotlinArrayList?.add(KotlinDataClass("Tech360Zone", Date()))
        kotlinArrayList?.add(KotlinDataClass("Do SUBSCRIBE", Date()))
        kotlinArrayList?.add(KotlinDataClass("DO LIKE", Date()))
        kotlinArrayList?.add(KotlinDataClass("DO Comment", Date()))
        kotlinArrayList?.add(KotlinDataClass("Do Share", Date()))
        kotlinArrayList?.add(KotlinDataClass("Tech360Zone", Date()))
        kotlinArrayList?.add(KotlinDataClass("Do SUBSCRIBE", Date()))
        kotlinArrayList?.add(KotlinDataClass("DO LIKE", Date()))
        kotlinArrayList?.add(KotlinDataClass("DO Comment", Date()))
        kotlinArrayList?.add(KotlinDataClass("Do Share", Date()))
        kotlinArrayList?.add(KotlinDataClass("Tech360Zone", Date()))
        kotlinArrayList?.add(KotlinDataClass("Do SUBSCRIBE", Date()))
        kotlinArrayList?.add(KotlinDataClass("DO LIKE", Date()))
        kotlinArrayList?.add(KotlinDataClass("DO Comment", Date()))
        kotlinArrayList?.add(KotlinDataClass("Do Share", Date()))
        kotlinArrayList?.add(KotlinDataClass("Tech360Zone", Date()))
        kotlinArrayList?.add(KotlinDataClass("Do SUBSCRIBE", Date()))
        kotlinArrayList?.add(KotlinDataClass("DO LIKE", Date()))
        kotlinArrayList?.add(KotlinDataClass("DO Comment", Date()))
        kotlinArrayList?.add(KotlinDataClass("Do Share", Date()))
        kotlinArrayList?.add(KotlinDataClass("Tech360Zone", Date()))
        kotlinArrayList?.add(KotlinDataClass("Do SUBSCRIBE", Date()))
        kotlinArrayList?.add(KotlinDataClass("DO LIKE", Date()))
        kotlinArrayList?.add(KotlinDataClass("DO Comment", Date()))
        kotlinArrayList?.add(KotlinDataClass("Do Share", Date()))
        kotlinArrayList?.add(KotlinDataClass("Tech360Zone", Date()))
        kotlinArrayList?.add(KotlinDataClass("Do SUBSCRIBE", Date()))
        kotlinArrayList?.add(KotlinDataClass("DO LIKE", Date()))
        kotlinArrayList?.add(KotlinDataClass("DO Comment", Date()))
        kotlinArrayList?.add(KotlinDataClass("Do Share", Date()))

        rvKotlinMain = binding.rvKotlinMain
        rvKotlinMain?.setHasFixedSize(true)
        rvKotlinMain?.layoutManager = getKotlinAdjustedGridLayoutManager(this)
        kotlinAdapter = KotlinAdapter(this, kotlinArrayList?:return)
        rvKotlinMain?.adapter = kotlinAdapter
    }

    fun getKotlinAdjustedGridLayoutManager(context: Context): GridLayoutManager {
        val layoutManager = GridLayoutManager(context, 2)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when(kotlinAdapter?.getItemViewType(position)) {
                    // Show 2 Columns when items are normal products
                    // 1 Means that it will take only cell for each item
                    // Now, our GridLayoutManager is of 2 cells, so each row
                    // will show 2 cells.
                    KotlinAdapter.ITEM_VIEW -> 1

                    // Show Full Width single item when its footer
                    // 2 means it will take 2 cells to create one full width cell.
                    KotlinAdapter.AD_VIEW -> 2

                    else -> 1
                }
            }
        }
        return layoutManager
    }
}