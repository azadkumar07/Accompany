package com.example.chandrakant.accompanyapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_realsignin.*

class Realsignin : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realsignin)


        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {  }
    }






    fun signIn(view : View) {

        mAuth!!.signInWithEmailAndPassword(etext.text.toString(),ptext.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext,"Welcome back", Toast.LENGTH_LONG).show()
                    val intent = Intent(applicationContext,SignIn::class.java)
                    startActivity(intent)
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
            }

    }

    fun signUp(view : View) {

        mAuth!!.createUserWithEmailAndPassword(etext.text.toString(),ptext.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext,"Welcome! you have signed up successfully", Toast.LENGTH_LONG).show()
                    val intent = Intent(applicationContext,SignIn::class.java)
                    startActivity(intent)
                }
            }.addOnFailureListener { exception ->
                if (exception != null) {
                    Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }

    }


}
