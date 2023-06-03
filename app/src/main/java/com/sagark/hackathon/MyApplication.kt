package com.sagark.hackathon

import android.app.Application
import com.google.firebase.FirebaseApp
import com.sagark.hackathon.models.User

class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }

    companion object {
        var user: User? = null
    }
}