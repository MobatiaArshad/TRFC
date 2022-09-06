package com.a71cities.trfc.views.events.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a71cities.trfc.databinding.TeamVsTeamRecLytBinding
import com.a71cities.trfc.utils.loadGlide
import com.a71cities.trfc.views.events.model.MatchResponse

class TeamVsTeamAdapter(val arrayList: List<MatchResponse.Data>): RecyclerView.Adapter<TeamVsTeamAdapter.TVTVH>() {

    inner class TVTVH(val binding: TeamVsTeamRecLytBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVTVH =
        TVTVH(TeamVsTeamRecLytBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: TVTVH, position: Int) {
        holder.binding.apply {
            holder.itemView.apply {
                arrayList[position].let {

                    when (it.matchType) {
                        "home" -> {
                            logoLeft.loadGlide(it.myteam?.image?.url)
                            leftTeamName.text = it.myteam?.name

                            logoRight.loadGlide(it.opponentLogo?.url)
                            rightTeamName.text = it.opponentName
                        }

                        else -> {
                            logoLeft.loadGlide(it.opponentLogo?.url)
                            leftTeamName.text = it.opponentName

                            logoRight.loadGlide(it.myteam?.image?.url)
                            rightTeamName.text = it.myteam?.name

                        }
                    }

                }
            }
        }
    }

    override fun getItemCount(): Int = arrayList.size
}