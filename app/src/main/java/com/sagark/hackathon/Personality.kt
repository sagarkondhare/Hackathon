package com.sagark.hackathon


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout

class Personality : AppCompatActivity() {
    var btngoalsetting:Button? =null
    var btnstress:Button? =null
    var btnconf:Button? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personality)
        btngoalsetting=findViewById<Button>(R.id.btngoalsetting)
        btnconf=findViewById<Button>(R.id.confidence)
        btnstress=findViewById<Button>(R.id.stressmanagement)
       // var lytTopics = findViewById<LinearLayout>(R.id.lytTopics)
        btngoalsetting?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, PlayAudio::class.java)
            intent.putExtra("filename", "goals_planning.mp3")
            intent.putExtra("title","Goals Setting and Planning")
            startActivity(intent)
        })
        btnstress?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, PlayAudio::class.java)
            intent.putExtra("filename", "stress_management.mp3")
            intent.putExtra("title","Self Confidence")

            startActivity(intent)
        })
        btnconf?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, PlayAudio::class.java)
            intent.putExtra("filename", "confidence.mp3")
            intent.putExtra("title","Stress Management")

            startActivity(intent)
        })

    }
}