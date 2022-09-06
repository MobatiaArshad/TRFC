package com.a71cities.trfc.views.signIn

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.a71cities.currencyconverter.extras.BaseFragment
import com.a71cities.trfc.R
import com.a71cities.trfc.databinding.FragmentSignInBinding

class SignInFragment : BaseFragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    override lateinit var viewModel: SignInViewModel
    lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SignInViewModel::class.java]
        binding.model = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.logInData.observe(viewLifecycleOwner) {
            findNavController().popBackStack(R.id.signInFragment,true)
            findNavController().navigate(R.id.homeFragment)
        }

        binding.goToSignUp.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }
    }


}