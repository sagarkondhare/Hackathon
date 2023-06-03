package com.sagark.hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.airbnb.lottie.LottieAnimationView

class GameActivity : AppCompatActivity() {
    lateinit var btnSearch: View
    lateinit var animSearching: LottieAnimationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        btnSearch = findViewById(R.id.btnSearch)
        animSearching = findViewById(R.id.animSearching)
        btnSearch.setOnClickListener {
            showSearchingUI()
        }
    }

    fun showSearchingUI() {
        btnSearch.visibility = View.GONE
        animSearching.visibility = View.VISIBLE

    }
}