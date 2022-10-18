package com.example.multirecyclerviewads

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.multirecyclerviewads.databinding.ActivityMainBinding
import com.example.multirecyclerviewads.databinding.NativeAdContainerBinding
import com.example.multirecyclerviewads.java.JavaMainActivity
import com.example.multirecyclerviewads.kotlin.KotlinMainActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnJava.setOnClickListener {
            startActivity(Intent(this,JavaMainActivity::class.java))
        }

        binding.btnKotlin.setOnClickListener {
            startActivity(Intent(this,KotlinMainActivity::class.java))
        }



    }
}