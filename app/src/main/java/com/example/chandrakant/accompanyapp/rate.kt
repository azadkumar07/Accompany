package com.example.chandrakant.accompanyapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import hotchemi.android.rate.AppRate

class rate : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate)
        AppRate.with(this)
            .setInstallDays(1)
            .setLaunchTimes(1)
            .setRemindInterval(1)
            .monitor()

        AppRate.showRateDialogIfMeetsConditions(this)
        AppRate.with(this).showRateDialog(this)
    }
}
