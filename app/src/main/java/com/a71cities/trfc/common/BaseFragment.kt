package com.a71cities.currencyconverter.extras

import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    abstract val viewModel: BaseViewModel

    override fun onStart() {
        super.onStart()

    }
}