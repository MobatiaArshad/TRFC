package com.a71cities.trfc.views.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a71cities.trfc.databinding.SliderImgRecLytBinding
import com.a71cities.trfc.views.home.model.SliderData

class SliderImageAdapter(val array: ArrayList<SliderData>): RecyclerView.Adapter<SliderImageAdapter.SIADA>() {

    inner class SIADA(val binding: SliderImgRecLytBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SIADA =
        SIADA(SliderImgRecLytBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: SIADA, position: Int) {
       holder.binding.model = array[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = array.size
}