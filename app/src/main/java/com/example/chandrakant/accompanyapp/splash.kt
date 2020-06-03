package com.example.chandrakant.accompanyapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import java.util.*

class splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
      Timer().schedule( object : TimerTask(){
          override fun run() {
              val intent = Intent(applicationContext,Realsignin::class.java)
              startActivity(intent)
          }


      },1500L)


    }
}
