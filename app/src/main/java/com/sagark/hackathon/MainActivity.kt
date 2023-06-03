package com.sagark.hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.learningExpress).setOnClickListener {
            startActivity(Intent(this, LearnActivity::class.java))
        }

        findViewById<View>(R.id.funExpress).setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }
    }
}