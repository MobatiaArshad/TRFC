package com.a71cities.trfc.views.devisions.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a71cities.trfc.databinding.DevisionRecLytBinding
import com.a71cities.trfc.views.devisions.model.DevisionReponse

class DevisionRecAdapter(val array: List<DevisionReponse.Data>, val click: (DevisionReponse.Data) -> Unit): RecyclerView.Adapter<DevisionRecAdapter.DVNADA>() {

    inner class DVNADA(val binding: DevisionRecLytBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DVNADA =
        DVNADA(DevisionRecLytBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: DVNADA, position: Int) {
        holder.binding.model = array[position]
        holder.binding.executePendingBindings()

        holder.itemView.setOnClickListener { click.invoke(array[position]) }
    }

    override fun getItemCount(): Int = array.size
}