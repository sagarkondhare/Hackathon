import android.content.res.AssetFileDescriptor
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.sagark.hackathon.R
import java.io.IOException

class PlayAudio : AppCompatActivity(), MediaPlayer.OnPreparedListener {
    private var mediaPlayer: MediaPlayer? = null
    private var playpause: ImageView? = null
    private var playpauseflag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_audio)

        playpause = findViewById(R.id.buttonPlayPause)
        val fileName = intent.getStringExtra("filename")

        playpause?.setOnClickListener(View.OnClickListener {
            if (playpauseflag) {
                playpauseflag = false
                mediaPlayer?.pause()
                playpause?.setImageResource(R.drawable.pause)
            } else {
                mediaPlayer?.start()
                playpauseflag = true
                playpause?.setImageResource(R.drawable.play)
            }
        })

        // Initialize the MediaPlayer
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer?.setOnPreparedListener(this)

        try {
            // Get the AssetFileDescriptor for the audio file
            val assetFileDescriptor: AssetFileDescriptor = assets.openFd(fileName!!)
            mediaPlayer?.setDataSource(assetFileDescriptor.fileDescriptor, assetFileDescriptor.startOffset, assetFileDescriptor.length)

            // Prepare the MediaPlayer asynchronously
            mediaPlayer?.prepareAsync()
            assetFileDescriptor.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // Release the MediaPlayer resources when the activity is destroyed
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onPrepared(mp: MediaPlayer?) {
        // Start playing the audio file
        playpause?.setImageResource(R.drawable.pause)
        mediaPlayer?.start()
    }
}
