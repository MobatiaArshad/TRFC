package com.a71cities.trfc.views.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.a71cities.trfc.R
import com.a71cities.trfc.databinding.FragmentHomeBinding
import com.a71cities.trfc.views.home.adapter.HomeCategoriesAdapter
import com.a71cities.trfc.views.home.adapter.SliderImageAdapter
import kotlin.math.abs

class HomeFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    val handler = Handler()
    var runnable: Runnable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding.model = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getBannerItems().observe(viewLifecycleOwner) {

            binding.imageSliderViewPager.apply {
                clipToPadding = false
                clipChildren = false
                offscreenPageLimit = 4
                getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
                adapter = SliderImageAdapter(it)


            }

            autoScroll(it.size)
        }

        binding.goToTeams.setOnClickListener(this)
        binding.goToGallery.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){

            binding.goToTeams.id -> {
                findNavController().navigate(R.id.devisionsFragment)
            }

            binding.goToGallery.id -> {
                findNavController().navigate(R.id.galleryFragment)
            }
        }
    }












    private fun autoScroll(count: Int) {

        runnable = Runnable {
            var pos: Int = binding.imageSliderViewPager.currentItem
            pos += 1
            if (pos >= count) pos = 0
            binding.imageSliderViewPager.currentItem = pos
            handler.postDelayed(runnable!!, 5000)
        }
        handler.postDelayed(runnable!!, 5000)
    }

    override fun onDestroy() {
        if (runnable != null) handler.removeCallbacks(runnable!!)
        super.onDestroy()
    }


}