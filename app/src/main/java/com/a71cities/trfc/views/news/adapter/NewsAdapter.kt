package com.a71cities.trfc.views.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a71cities.trfc.databinding.NewsRecLytBinding
import com.a71cities.trfc.utils.isCommented
import com.a71cities.trfc.utils.isLiked
import com.a71cities.trfc.views.news.model.NewsReponse

class NewsAdapter(val array: List<NewsReponse.Data>,val click:(NewsReponse.Data) -> Unit): RecyclerView.Adapter<NewsAdapter.NWSADA>() {

    inner class NWSADA(val binding: NewsRecLytBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NWSADA =
        NWSADA(NewsRecLytBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: NWSADA, position: Int) {
        holder.binding.model = array[position]
        holder.binding.executePendingBindings()

        holder.binding.apply {
            holder.itemView.apply {
                array[position].let {
                    include.likeBtn.isLiked(it.liked)

                    include.likeCount.text = it.likes.toString()

                    include.likeBtn.setOnClickListener { v ->
                        if (it.liked){
                            it.liked = true
                            it.likes = it.likes - 1


                        } else {
                            it.liked = false
                            it.likes = it.likes + 1


                        }

                        notifyItemChanged(position)
                    }
                }
            }
        }


    }

    override fun getItemCount(): Int = array.size
}