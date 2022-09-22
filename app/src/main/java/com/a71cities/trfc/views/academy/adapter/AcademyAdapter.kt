package com.a71cities.trfc.views.academy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a71cities.trfc.databinding.AcademyRecLytBinding
import com.a71cities.trfc.views.academy.model.AcademyResponse

class AcademyAdapter(val array: List<AcademyResponse.Data>): RecyclerView.Adapter<AcademyAdapter.ACAADA>() {

    inner class ACAADA(val binding: AcademyRecLytBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ACAADA =
        ACAADA(AcademyRecLytBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ACAADA, position: Int) {
        holder.binding.model = array[position]
        holder.binding.executePendingBindings()

    }

    override fun getItemCount(): Int = array.size
}