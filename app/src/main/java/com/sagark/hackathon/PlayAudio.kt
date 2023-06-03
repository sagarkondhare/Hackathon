package com.sagark.hackathon
import android.content.res.AssetFileDescriptor
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException

class PlayAudio : AppCompatActivity(), MediaPlayer.OnPreparedListener {
    private var mediaPlayer: MediaPlayer? = null
    private var playpause: ImageView? = null
    private var  textViewTitle: TextView?=null
    private var seekBar: SeekBar? = null
    private var playpauseflag = true
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_audio)

        playpause = findViewById(R.id.buttonPlayPause)
        seekBar = findViewById(R.id.seekBarProgress)
        textViewTitle = findViewById(R.id.textViewTitle)
        val fileName = intent.getStringExtra("filename")
        val title = intent.getStringExtra("title")
        textViewTitle?.setText(title)
        playpause?.setOnClickListener(View.OnClickListener {
            if (playpauseflag) {
                playpauseflag = false
                mediaPlayer?.pause()
                playpause?.setImageResource(R.drawable.play)
            } else {
                mediaPlayer?.start()
                playpauseflag = true
                playpause?.setImageResource(R.drawable.pause)
            }
        })

        // Initialize the MediaPlayer
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer?.setOnPreparedListener(this)

        try {
            // Get the AssetFileDescriptor for the audio file
            val assetFileDescriptor: AssetFileDescriptor = assets.openFd(fileName!!)
            mediaPlayer?.setDataSource(
                assetFileDescriptor.fileDescriptor,
                assetFileDescriptor.startOffset,
                assetFileDescriptor.length
            )

            // Prepare the MediaPlayer asynchronously
            mediaPlayer?.prepareAsync()
            assetFileDescriptor.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onPrepared(mp: MediaPlayer?) {
        // Start playing the audio file
        playpause?.setImageResource(R.drawable.pause)
        mediaPlayer?.start()

        // Set the maximum value of the SeekBar to the duration of the audio file
        seekBar?.max = mediaPlayer?.duration ?: 0

        // Start updating the SeekBar progress
        updateSeekBarProgress()
    }

    private fun updateSeekBarProgress() {
        // Update the SeekBar progress every second
        seekBar?.progress = mediaPlayer?.currentPosition ?: 0

        // Schedule the update after 1 second
        handler.postDelayed({ updateSeekBarProgress() }, 1000)
    }
}