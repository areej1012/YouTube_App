package com.example.youtubeapp

import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.GridView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapp.databinding.ActivityMainBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var videoArray: ArrayList<Video>
    lateinit var player: YouTubePlayer
    var currentVideo = "F9UC9DY-vIU"
    var timeStamp = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkConnection()
        videoArray = arrayListOf(
            Video("Kotlin Course - Tutorial for Beginners", "F9UC9DY-vIU"),
            Video("Learn Java in One Video", "drQK8ciCAjY"),
            Video("Should you learn Swift in 2021?","PyRo2_-vnn0"),
            Video("Alec Benjamin - Let Me Down Slowly","50VNCymT-Cs"),
            Video("Username and Password", "G_XYXuC8b9M"),
            Video("GUI Username and Password", "sqJWyPhZkDw"),
            Video("Database Module", "E-Kb6FgMbVw")
        )

        lifecycle.addObserver(binding.youtubePlayerView)
        binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                player = youTubePlayer
                player.loadVideo(currentVideo,0F)
                initRV()
            }
        })

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("currentTitle",videoArray[0].code)
        outState.putFloat("currentTime",0f)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        currentVideo = savedInstanceState.getString("currentVideo", "F9UC9DY-vIU")
        timeStamp = savedInstanceState.getFloat("timeStamp", 0f)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            binding.youtubePlayerView.enterFullScreen()
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            binding.youtubePlayerView.exitFullScreen()
        }
    }

    fun initRV(){
//        binding.rv.adapter = VideoAdpater(videoArray,player)
//        binding.rv.layoutManager = LinearLayoutManager(this)

        binding.gv.adapter = GridViewApdter(this, player ,videoArray)
    }
    fun checkConnection(){
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        if (activeNetwork?.isConnectedOrConnecting  == false){
            AlertDialog.Builder(this)
                .setTitle("Internet Connection Not Found")
                .setPositiveButton("RETRY"){_, _ -> checkConnection()}
                .show()
        }
    }
}