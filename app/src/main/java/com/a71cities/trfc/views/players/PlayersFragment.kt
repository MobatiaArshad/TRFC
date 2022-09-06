package com.a71cities.trfc.views.players

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a71cities.currencyconverter.extras.BaseFragment
import com.a71cities.trfc.R
import com.a71cities.trfc.databinding.FragmentPlayersBinding
import com.a71cities.trfc.databinding.PlayersRecLytBinding
import com.a71cities.trfc.extras.Constants.PLAYER_ID
import com.a71cities.trfc.views.players.adapter.PlayersRecAdapter

class PlayersFragment : BaseFragment() {

    companion object {
        fun newInstance() = PlayersFragment()
    }

    override lateinit var viewModel: PlayersViewModel
    lateinit var binding: FragmentPlayersBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayersBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[PlayersViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.passedId.value = arguments?.getString(PLAYER_ID)

        viewModel.getPlayers()

        viewModel.playerData.observe(viewLifecycleOwner) {
            binding.recyclerView.adapter = PlayersRecAdapter(it)
        }

    }

}