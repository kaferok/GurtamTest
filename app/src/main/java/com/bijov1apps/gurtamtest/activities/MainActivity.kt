package com.bijov1apps.gurtamtest.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bijov1apps.gurtamtest.R
import com.bijov1apps.gurtamtest.common.ActivityBinder
import com.bijov1apps.gurtamtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main), ActivityBinder {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.bottomNavigation.setupWithNavController(findNavController(R.id.mainContainer))
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