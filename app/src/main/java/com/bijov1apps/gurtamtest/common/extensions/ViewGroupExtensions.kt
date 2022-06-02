package com.bijov1apps.gurtamtest.common.extensions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.google.android.material.tabs.TabLayout

fun ViewGroup.inflate(@LayoutRes id: Int) =
    LayoutInflater.from(context).inflate(id, this, false)
