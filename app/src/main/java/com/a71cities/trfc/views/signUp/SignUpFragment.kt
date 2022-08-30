package com.a71cities.trfc.views.signUp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a71cities.currencyconverter.extras.BaseFragment
import com.a71cities.trfc.R
import com.a71cities.trfc.databinding.FragmentSignUpBinding

class SignUpFragment : BaseFragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    override lateinit var viewModel: SignUpViewModel
    lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        binding.model = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        viewModel.registerData.observe(viewLifecycleOwner) {

        }
    }

}