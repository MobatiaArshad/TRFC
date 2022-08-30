package com.a71cities.trfc.views.gallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a71cities.trfc.databinding.GalleryRecLytBinding
import com.a71cities.trfc.utils.loadGlide
import com.a71cities.trfc.views.gallery.model.GalleryResponse

class GalleryAdapter(
    val arrayList: List<GalleryResponse.Data>,
    private val click: (GalleryResponse.Data) -> Unit
) : RecyclerView.Adapter<GalleryAdapter.GLLRYADA>() {

    inner class GLLRYADA(val binding: GalleryRecLytBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GLLRYADA =
        GLLRYADA(GalleryRecLytBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: GLLRYADA, position: Int) {
        holder.binding.model = arrayList[position].image
        holder.binding.executePendingBindings()
        println("DATA: "+arrayList[position].image?.url)
//        holder.binding.galleryImg.loadGlide(arrayList[position].image?.url)

        holder.itemView.setOnClickListener { click.invoke(arrayList[position]) }
    }

    override fun getItemCount(): Int = arrayList.size
}