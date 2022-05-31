package com.bijov1apps.gurtamtest.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bijov1apps.gurtamtest.R
import com.bijov1apps.gurtamtest.common.ActivityBinder

class MainActivity : AppCompatActivity(), ActivityBinder {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun toolbar(isVisible: Boolean, isShowIcon: Boolean, title: String?) {
        if (isVisible) {
            supportActionBar?.show()
        } else {
            supportActionBar?.hide()
        }
        supportActionBar?.title = title
        val arrowBack = if (isShowIcon) {
            ContextCompat.getDrawable(this, R.drawable.ic_baseline_arrow_white_24)
        } else {
            null
        }
        supportActionBar?.setIcon(arrowBack)
    }
}