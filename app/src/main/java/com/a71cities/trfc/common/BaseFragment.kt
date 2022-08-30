package com.a71cities.currencyconverter.extras

import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    abstract val viewModel: BaseViewModel

    override fun onStart() {
        super.onStart()

        viewModel.showAlertRes.observe(this) {
            Toast.makeText(context, getString(it!!), Toast.LENGTH_SHORT).show()
        }

        viewModel.showAlertTxt.observe(this) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }
}