package com.bijov1apps.gurtamtest.utils

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(action: NavDirections, host: Int? = null) {
    val navController = if (host == null) {
        findNavController()
    } else {
        Navigation.findNavController(requireActivity(), host)
    }
    navController.navigate(action)
}
