package com.viona.moviecatalogue.ui.tv_show.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.viona.moviecatalogue.R

class DetailTVShowActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)
    }
}