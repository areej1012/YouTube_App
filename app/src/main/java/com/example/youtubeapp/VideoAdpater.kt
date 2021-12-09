package com.example.youtubeapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapp.databinding.ItemRowBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer

class VideoAdpater(var videoArray : ArrayList<Video>, val player : YouTubePlayer) : RecyclerView.Adapter<VideoAdpater.ViewHolder>() {
    class ViewHolder(var binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = videoArray
        holder.binding.btDisplay.text = list[position].title
        holder.binding.btDisplay.setOnClickListener {
            player.loadVideo(list[position].code, 0F)
        }
    }

    override fun getItemCount(): Int = videoArray.size
}