package com.a71cities.trfc.views.devisions

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.a71cities.currencyconverter.extras.BaseFragment
import com.a71cities.trfc.R
import com.a71cities.trfc.databinding.FragmentDevisionsBinding
import com.a71cities.trfc.extras.Constants.PLAYER_ID
import com.a71cities.trfc.views.devisions.adapter.DevisionRecAdapter

class DevisionsFragment : BaseFragment() {

    companion object {
        fun newInstance() = DevisionsFragment()
    }

    override lateinit var viewModel: DevisionsViewModel
    private lateinit var binding: FragmentDevisionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDevisionsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[DevisionsViewModel::class.java]

        viewModel.divisionArray.observe(viewLifecycleOwner) {
            binding.recyclerView.adapter = DevisionRecAdapter(it) { div ->
                findNavController().navigate(R.id.playersFragment,
                    bundleOf(PLAYER_ID to div.id)
                )
            }
        }

        viewModel.loader.observe(viewLifecycleOwner) {
            binding.shimmerLayout.isVisible = it
            if (it) binding.shimmerLayout.startShimmer() else binding.shimmerLayout.stopShimmer()
        }
    }


}