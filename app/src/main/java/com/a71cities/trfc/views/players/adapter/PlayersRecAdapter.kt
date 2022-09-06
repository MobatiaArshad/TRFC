package com.a71cities.trfc.views.players.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a71cities.trfc.databinding.PlayersRecLytBinding
import com.a71cities.trfc.views.players.models.PlayersResponse

class PlayersRecAdapter(val array: List<PlayersResponse.Data>): RecyclerView.Adapter<PlayersRecAdapter.PLRADA>() {

    inner class PLRADA(val binding: PlayersRecLytBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PLRADA =
        PLRADA(PlayersRecLytBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: PLRADA, position: Int) {
        holder.binding.model = array[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = array.size
}