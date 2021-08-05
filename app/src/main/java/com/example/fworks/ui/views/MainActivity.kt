package com.example.fworks.ui.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.fworks.databinding.ActivityMainBinding
import com.example.fworks.ui.adapter.ViewPagerAdapter


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.viewPagerTab.setupWithViewPager(binding.vpGif)
        binding.vpGif.apply{
            adapter = ViewPagerAdapter(supportFragmentManager, this@MainActivity)
            offscreenPageLimit = 2
        }
    }
}