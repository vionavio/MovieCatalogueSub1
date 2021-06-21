package com.viona.moviecatalogue.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.viona.moviecatalogue.R
import com.viona.moviecatalogue.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var activityMainBinding: ActivityMainBinding? = null
    private val binding get() = activityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
    }

    private fun initUI() {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding?.viewPager?.adapter = sectionsPagerAdapter
        binding?.tab?.setupWithViewPager(binding?.viewPager)

        supportActionBar?.title = getString(R.string.app_name)
    }

    override fun onBackPressed() {
        if (binding?.viewPager?.currentItem == 0) {
            super.onBackPressed()
        } else {
            binding?.viewPager?.currentItem =
                binding?.viewPager?.currentItem?.minus(1)!!
        }
    }
}