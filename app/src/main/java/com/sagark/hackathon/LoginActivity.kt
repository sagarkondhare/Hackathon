package com.sagark.hackathon

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sagark.hackathon.models.User


class LoginActivity : AppCompatActivity() {
    private val TAG: String = LoginActivity::class.java.name
    private val RC_SIGN_IN = 123
    private var googleSignInClient: GoogleSignInClient? = null
    private var firebaseAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.your_web_client_id))
                .requestEmail()
                .build()

            googleSignInClient = GoogleSignIn.getClient(this, gso)

            firebaseAuth = FirebaseAuth.getInstance()
            var login = findViewById<View>(R.id.sign_in_button)

            login.setOnClickListener {
                signIn()
            }
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Sign in with Google account
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
                CommonMethods.saveStringPreferences(this, "user_id", account.id)
                val database: FirebaseDatabase = FirebaseDatabase.getInstance()
                val userRef: DatabaseReference = database.getReference("users/" + account.id)
                MyApplication.user = User(
                    userName = account.displayName,
                    userId = account.id,
                    email = account.email,
                    image = if (account.photoUrl != null) account.photoUrl.toString() else ""
                )
                userRef.setValue(MyApplication.user)

                finish()
                startActivity(Intent(this, MainActivity::class.java))
            } catch (e: ApiException) {
                // Handle sign-in failure
                Log.w(TAG, "Google sign-in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth!!.signInWithCredential(credential)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    // Sign-in success, update UI with the signed-in user's information
                    val user = firebaseAuth!!.currentUser
                    // You can retrieve user information from the 'user' object
                } else {
                    // Sign-in failed, display an error message to the user
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(
                        this@LoginActivity, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}