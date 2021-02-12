package com.viona.moviecatalogue.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.viona.moviecatalogue.R
import com.viona.moviecatalogue.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUI()
    }

    private fun initUI() {
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        activityMainBinding.viewPager.adapter = sectionsPagerAdapter
        activityMainBinding.tab.setupWithViewPager(activityMainBinding.viewPager)

        supportActionBar?.title = getString(R.string.movies)
    }
}