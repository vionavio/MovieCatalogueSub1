package com.viona.moviecatalogue.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.viona.moviecatalogue.utils.Constants

open class BaseActivity : AppCompatActivity() {

    fun gotoActivity(destActivity: Class<*>?, data: String?) {
        val intent = Intent(this, destActivity)
        intent.putExtra(Constants.EXTRA_TV_SHOW, data)
        startActivity(intent)
    }
}