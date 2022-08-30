package com.a71cities.trfc.views.gallery

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a71cities.currencyconverter.extras.BaseFragment
import com.a71cities.trfc.R
import com.a71cities.trfc.databinding.FragmentGalleryBinding
import com.a71cities.trfc.views.gallery.adapter.GalleryAdapter

class GalleryFragment : BaseFragment() {

    companion object {
        fun newInstance() = GalleryFragment()
    }

    override lateinit var viewModel: GalleryViewModel
    private lateinit var binding: FragmentGalleryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGalleryBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[GalleryViewModel::class.java]

        viewModel.galleryArray.observe(viewLifecycleOwner) {
            binding.recyclerView.adapter = GalleryAdapter(it) {

            }
        }
    }



}