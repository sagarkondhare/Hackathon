package com.sagark.hackathon

import android.content.res.AssetFileDescriptor
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.IOException

class PlayAudio : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_audio)
        var fileName = intent.getStringExtra("fileName")
        // Initialize the MediaPlayer
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setAudioStreamType(AudioManager.STREAM_MUSIC)

        try {
            // Get the AssetFileDescriptor for the audio file
            val assetFileDescriptor: AssetFileDescriptor = assets.openFd(fileName!!)

            // Set the data source for the MediaPlayer
            mediaPlayer?.setDataSource(
                assetFileDescriptor.fileDescriptor,
                assetFileDescriptor.startOffset,
                assetFileDescriptor.length
            )

            // Prepare the MediaPlayer asynchronously
            mediaPlayer?.prepareAsync()

            // Set a completion listener to release the MediaPlayer resources when playback is complete
            mediaPlayer?.setOnCompletionListener {
                mediaPlayer?.release()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // Start playing the audio file
        mediaPlayer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()

        // Release the MediaPlayer resources when the activity is destroyed
        mediaPlayer?.release()
        mediaPlayer = null
    }

}