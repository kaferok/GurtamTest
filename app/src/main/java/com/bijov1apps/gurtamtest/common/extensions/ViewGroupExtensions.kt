package com.bijov1apps.gurtamtest.common.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes id: Int) = LayoutInflater.from(context).inflate(id, this, false)