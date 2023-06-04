package com.sagark.hackathon


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView

class Personality : AppCompatActivity() {
    var btngoalsetting:Button? =null
    var btnstress:Button? =null
    var btnconf:Button? =null
    var language_flag_hindi = false;   ///true for hindi
    var idSwitch:Switch? =null
    var enhi:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personality)
        btngoalsetting=findViewById<Button>(R.id.btngoalsetting)
        btnconf=findViewById<Button>(R.id.confidence)
        btnstress=findViewById<Button>(R.id.stressmanagement)
        idSwitch = findViewById<Switch>(R.id.aaa)
        enhi = findViewById<TextView>(R.id.enhi)
       // var lytTopics = findViewById<LinearLayout>(R.id.lytTopics)
        btngoalsetting?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, PlayAudio::class.java)
            if(language_flag_hindi ){
                intent.putExtra("filename", "goals_planning_hindi.mp3")

            }else{
                intent.putExtra("filename", "goals_planning.mp3")

            }
            intent.putExtra("title","Goals Setting and Planning")
            startActivity(intent)
        })

        idSwitch?.setOnCheckedChangeListener { buttonView, isChecked ->
            // on below line we are checking
            // if switch is checked or not.
            if (isChecked) {
                // on below line we are setting text
                // if switch is checked.
                language_flag_hindi=false
                enhi?.setText("Eng")
            } else {
                language_flag_hindi = true
                enhi?.setText("Hin")
                // on below line we are setting text
                // if switch is unchecked.
            }
        }
        btnstress?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, PlayAudio::class.java)
            if(language_flag_hindi ){
                intent.putExtra("filename", "stress_management_hindi.mp3")

            }else{
                intent.putExtra("filename", "stress_management.mp3")

            }
            intent.putExtra("title","Self Confidence")

            startActivity(intent)
        })
        btnconf?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, PlayAudio::class.java)
            if(language_flag_hindi ){
                intent.putExtra("filename", "confidence_hindi.mp3")

            }else{
                intent.putExtra("filename", "confidence.mp3")

            }
            intent.putExtra("title","Stress Management")

            startActivity(intent)
        })

    }
}