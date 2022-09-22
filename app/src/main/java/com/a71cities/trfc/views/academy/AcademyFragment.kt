package com.a71cities.trfc.views.academy

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a71cities.currencyconverter.extras.BaseFragment
import com.a71cities.trfc.databinding.FragmentAcademyBinding
import com.a71cities.trfc.views.academy.adapter.AcademyAdapter

class AcademyFragment : BaseFragment() {

    companion object {
        fun newInstance() = AcademyFragment()
    }

    override lateinit var viewModel: AcademyViewModel
    private lateinit var binding: FragmentAcademyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAcademyBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[AcademyViewModel::class.java]

        viewModel.data.observe(viewLifecycleOwner) {
            binding.recyclerView.adapter = AcademyAdapter(it)
        }


    }

}