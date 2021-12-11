package com.example.youtubeapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer

class GridViewApdter(private val context: Context, private val player: YouTubePlayer,
                     private val videoList: ArrayList<Video>,) : BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    lateinit var button :Button
    override fun getCount(): Int = videoList.size

    override fun getItem(p0: Int): Any = videoList[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var convertView = p1
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.item_row, null)
        }
        button = convertView!!.findViewById(R.id.btDisplay)
        button.text = videoList[p0].title
        button.setOnClickListener {
            player.loadVideo(videoList[p0].code,0f)
        }
        return convertView
    }
}