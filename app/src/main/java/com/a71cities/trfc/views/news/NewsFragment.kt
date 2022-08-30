package com.a71cities.trfc.views.news

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a71cities.currencyconverter.extras.BaseFragment
import com.a71cities.trfc.R
import com.a71cities.trfc.databinding.FragmentNewsBinding
import com.a71cities.trfc.views.news.adapter.NewsAdapter

class NewsFragment : BaseFragment() {

    companion object {
        fun newInstance() = NewsFragment()
    }

    override lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        binding.model = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        viewModel.newsArray.observe(viewLifecycleOwner) {
            binding.recycler.adapter = NewsAdapter(it) {

            }
        }
    }

}