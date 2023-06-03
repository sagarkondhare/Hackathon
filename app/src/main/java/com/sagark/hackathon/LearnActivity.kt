package com.sagark.hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class LearnActivity : AppCompatActivity() {
    var  btnPersonality:Button? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn)
        btnPersonality=findViewById<Button>(R.id.btnPersonality)
        btnPersonality?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Personality::class.java)
            startActivity(intent)
        })
    }
}