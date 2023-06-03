package com.sagark.hackathon

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.sagark.hackathon.models.User


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
        val query: Query =
            FirebaseDatabase.getInstance().getReference("users").orderByChild("isSearching")
                .equalTo(true)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (childSnapshot in dataSnapshot.children) {
                    val yourModel: User? = childSnapshot.getValue(User::class.java)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }
}