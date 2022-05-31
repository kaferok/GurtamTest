package com.bijov1apps.gurtamtest.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class BaseFragmentViewBindingDelegate<T : ViewBinding>(
    private val fragment: Fragment
) : ReadOnlyProperty<Fragment, T> {
    internal var binding: T? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                super.onCreate(owner)
                fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            super.onDestroy(owner)
                            binding = null
                        }
                    })
                }
            }
        })
    }
}

class FragmentViewBindingDelegate<T : ViewBinding>(
    private val fragment: Fragment,
    private val viewBindingFactory: (View) -> T
) : BaseFragmentViewBindingDelegate<T>(fragment) {

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val binding = binding
        if (binding != null) {
            return binding
        }
        val lifecycle = fragment.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
        }
        return viewBindingFactory(thisRef.requireView()).also { this.binding = it }
    }
}

fun <T : ViewBinding> Fragment.viewBinding(
    viewBindingFactory: (View) -> T
) = FragmentViewBindingDelegate(this, viewBindingFactory)


class FragmentViewBindingInflaterDelegate<T : ViewBinding>(
    private val fragment: Fragment,
    private val viewBindingFactory: (LayoutInflater, ViewGroup, Boolean) -> T
) : BaseFragmentViewBindingDelegate<T>(fragment) {

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val binding = binding
        if (binding != null) {
            return binding
        }
        val lifecycle = fragment.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
        }
        val container = fragment.view?.parent as ViewGroup
        val inflater = LayoutInflater.from(fragment.requireContext())

        return viewBindingFactory(inflater, container, false).also { this.binding = it }
    }

}

fun <T : ViewBinding> Fragment.viewBinding(
    viewBindingFactory: (LayoutInflater, ViewGroup, Boolean) -> T
) = FragmentViewBindingInflaterDelegate(this, viewBindingFactory)