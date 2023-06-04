package com.sagark.hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import com.sagark.hackathon.models.Questions

class AskQuestions : AppCompatActivity() {
    lateinit var radio1: RadioButton
    lateinit var radio2: RadioButton
    lateinit var radio3: RadioButton
    var selectedIndex = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask_questions)
        val question1 = findViewById<EditText>(R.id.question1)
        val question2 = findViewById<EditText>(R.id.question2)
        val question3 = findViewById<EditText>(R.id.question3)
        radio1 = findViewById(R.id.radio1)
        radio2 = findViewById(R.id.radio2)
        radio3 = findViewById(R.id.radio3)
        radio1.setOnCheckedChangeListener { buttonView, isChecked ->
            select1(isChecked)
        }
        radio2.setOnCheckedChangeListener { buttonView, isChecked ->
            select2(isChecked)
        }
        radio3.setOnCheckedChangeListener { buttonView, isChecked ->
            select3(isChecked)
        }
        findViewById<View>(R.id.btnSubmit).setOnClickListener {
            if (selectedIndex == -1) {
                CommonMethods.shortToast(this, "Select Lie")
            } else {
                MyApplication.questions = Questions(
                    question1.text.toString(),
                    question2.text.toString(),
                    question3.text.toString(),
                    selectedIndex
                )
                CommonMethods.shortToast(this, "" + selectedIndex)
                startActivity(Intent(this, GameActivity::class.java))
                finish()
            }
        }
    }

    fun select1(isChecked: Boolean) {
        if (isChecked) {
            selectedIndex = 1
            radio2.isChecked = false
            radio3.isChecked = false
        } else {
//            selectedIndex = -1
        }
    }

    fun select2(isChecked: Boolean) {
        if (isChecked) {
            selectedIndex = 2
            radio1.isChecked = false
            radio3.isChecked = false
        } else {
//            selectedIndex = -1
        }
    }

    fun select3(isChecked: Boolean) {
        if (isChecked) {
            selectedIndex = 3
            radio1.isChecked = false
            radio2.isChecked = false
        } else {
//            selectedIndex = -1
        }
    }
}