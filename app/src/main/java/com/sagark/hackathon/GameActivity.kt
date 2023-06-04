package com.sagark.hackathon

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import com.sagark.hackathon.models.User


class GameActivity : AppCompatActivity() {
    private val TAG: String = "GameActivity"
    lateinit var btnSearch: View
    lateinit var animSearching: LottieAnimationView
    lateinit var database: FirebaseDatabase
    lateinit var myRef: DatabaseReference
    lateinit var query: Query
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("users/" + MyApplication.user?.userId)
        query = FirebaseDatabase.getInstance().getReference("users").orderByChild("searching")
            .equalTo(true)
        btnSearch = findViewById(R.id.btnSearch)
        animSearching = findViewById(R.id.animSearching)
        btnSearch.setOnClickListener {
            showSearchingUI()
        }
    }

    fun showSearchingUI() {
        btnSearch.visibility = View.GONE
        animSearching.visibility = View.VISIBLE

        MyApplication.user?.searching = true
        myRef.setValue(MyApplication.user)
        query.addValueEventListener(queryListener)
        myRef.addValueEventListener(myRefListener)
    }

    val queryListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (childSnapshot in dataSnapshot.children) {
                val user: User? = childSnapshot.getValue(User::class.java)
                if (user?.userId != MyApplication.user?.userId && user?.searching == true) {
                    myRef.removeEventListener(myRefListener)
                    animSearching.visibility = View.GONE
                    val userRef = database.getReference("users/" + user.userId)
                    user.playing = true
                    user.gameNode = "games/" + user.userId + MyApplication.user?.userId
                    userRef.setValue(user)
                    showGameUI()
                    CommonMethods.shortToast(
                        this@GameActivity,
                        "Match Found: " + user.userName + " ID: " + user.userId
                    )
                    return
                }
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {}
    }

    private fun showGameUI() {
        findViewById<View>(R.id.lytSearch).visibility = View.GONE
        findViewById<View>(R.id.lytGame).visibility = View.VISIBLE


    }

    override fun onDestroy() {
        super.onDestroy()
        MyApplication.user?.searching = false
        myRef.setValue(MyApplication.user)
    }

    val myRefListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            try {
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {
//                    query.removeEventListener(queryListener)
                    val value = Gson().fromJson(Gson().toJson(dataSnapshot.value), User::class.java)
                    if (value.userId != MyApplication.user?.userId) {
                        animSearching.visibility = View.GONE
                        CommonMethods.shortToast(
                            this@GameActivity,
                            "Match Found: " + value.userName + " ID: " + value.userId
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("error", dataSnapshot.toString())
                e.printStackTrace()
            }
        }

        override fun onCancelled(error: DatabaseError) {
            // Failed to read value
            Log.w(TAG, "Failed to read value.", error.toException())
        }
    }
}