package com.example.inout2020_aimers.MusicPlayer

import android.content.Context
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import com.example.inout2020_aimers.R
import com.example.inout2020_aimers.databinding.FragmentMusicPlayerBinding
import kotlinx.android.synthetic.main.fragment_details.view.*
import kotlinx.android.synthetic.main.fragment_music_player.*

class MusicPlayerFragment : Fragment() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var runnable: Runnable
    private var handler: Handler = Handler()
    private var pause: Boolean = false
    private var _binding: FragmentMusicPlayerBinding? = null
    private val binding
        get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMusicPlayerBinding.inflate(inflater, container, false)
        // Start the media player
        binding.playBtn.setOnClickListener {
            if(isNetworkAvailable()==false){
                Toast.makeText(activity,"NO internet",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (pause) {
                binding.tv.text="Tap to play"
                mediaPlayer.seekTo(mediaPlayer.currentPosition)
                mediaPlayer.start()
                pause = false
            } else {
                binding.tv.text="Now Playing"
                mediaPlayer = MediaPlayer.create(context, R.raw.fast_and)
                mediaPlayer.start()

            }
            initializeSeekBar()
            binding.tv.text="Now Playing"
            binding.playBtn.isEnabled = false
            binding.pauseBtn.isEnabled = true
            binding.stopBtn.isEnabled = true
            binding.stopBtn.visibility = VISIBLE
            binding.pauseBtn.visibility = VISIBLE
            binding.playBtn.visibility = GONE

            mediaPlayer.setOnCompletionListener {
                binding.tv.text="Tap to play"
                binding.playBtn.isEnabled = true
                binding.pauseBtn.isEnabled = false
                binding.stopBtn.isEnabled = false
                binding.stopBtn.visibility = GONE
                binding.pauseBtn.visibility = GONE
                binding.playBtn.visibility = VISIBLE
            }
        }
        // Pause the media player
        binding.pauseBtn.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                pause = true
                binding.playBtn.isEnabled = true
                binding.pauseBtn.isEnabled = false
                binding.stopBtn.isEnabled = true
                binding.stopBtn.visibility = GONE
                binding.pauseBtn.visibility = GONE
                binding.playBtn.visibility = VISIBLE
                binding.tv.text="Tap to play"
            }
        }
        // Stop the media player
        binding.stopBtn.setOnClickListener {
            if (mediaPlayer.isPlaying || pause.equals(true)) {
                pause = false
                seek_bar?.setProgress(0)
                mediaPlayer.stop()
                mediaPlayer.reset()
                mediaPlayer.release()
                handler.removeCallbacks(runnable)
                binding.tv.text="Tap to play"
                binding.playBtn.isEnabled = true
                binding.pauseBtn.isEnabled = false
                binding.stopBtn.isEnabled = false
                binding.stopBtn.visibility = GONE
                binding.pauseBtn.visibility = GONE
                binding.playBtn.visibility = VISIBLE
            }
        }
        // Seek bar change listener
        seek_bar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if (b) {
                    mediaPlayer.seekTo(i * 1000)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
        return binding.root
    }

    // Method to initialize seek bar and audio stats
    private fun initializeSeekBar() {
        seek_bar?.max = mediaPlayer.seconds

        runnable = Runnable {
            seek_bar?.progress = mediaPlayer.currentSeconds
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }

    // Creating an extension property to get the media player time duration in seconds
    val MediaPlayer.seconds: Int
        get() {
            return this.duration / 1000
        }

    // Creating an extension property to get media player current position in seconds
    val MediaPlayer.currentSeconds: Int
        get() {
            return this.currentPosition / 1000
        }

    @RequiresApi(Build.VERSION_CODES.M)
    fun isNetworkAvailable():Boolean{
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}