package com.lorevantonio.kotlinofflinefirst.app.di.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment() : Fragment() {

    abstract fun init(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View

    abstract fun subscribeObservable()

    private var viewRoot: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (viewRoot == null) {
            viewRoot = init(inflater, container, savedInstanceState)
            subscribeObservable()
        }
        return viewRoot
    }

}