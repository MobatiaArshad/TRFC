package com.a71cities.trfc.views.events.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a71cities.trfc.databinding.TeamVsTeamRecLytBinding

class TeamVsTeamAdapter: RecyclerView.Adapter<TeamVsTeamAdapter.TVTVH>() {

    inner class TVTVH(val binding: TeamVsTeamRecLytBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVTVH =
        TVTVH(TeamVsTeamRecLytBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: TVTVH, position: Int) {
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = 5
}