package com.a71cities.trfc.views.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a71cities.trfc.databinding.CommonTilesRecLytBinding
import com.a71cities.trfc.views.home.model.CategoriesData

class HomeCategoriesAdapter(val arrayList: ArrayList<CategoriesData>): RecyclerView.Adapter<HomeCategoriesAdapter.HCADA>() {

    inner class HCADA(val binding: CommonTilesRecLytBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HCADA =
        HCADA(CommonTilesRecLytBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: HCADA, position: Int) {
        holder.binding.model = arrayList[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = arrayList.size
}