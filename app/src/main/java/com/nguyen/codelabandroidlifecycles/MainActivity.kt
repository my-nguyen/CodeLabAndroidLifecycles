package com.nguyen.codelabandroidlifecycles

import android.os.Bundle
import android.os.SystemClock
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // The ViewModelStore provides a new ViewModel or one previously created.
        val chronometerViewModel = ViewModelProvider(this)[ChronometerViewModel::class.java]

        // Get the chronometer reference
        val chronometer = findViewById<Chronometer>(R.id.chronometer)

        if (chronometerViewModel.startTime == null) {
            // If the start date is not defined, it's a new ViewModel so set it.
            val startTime = SystemClock.elapsedRealtime()
            chronometerViewModel.startTime = startTime
            chronometer.base = startTime
        } else {
            // Otherwise the ViewModel has been retained, set the chronometer's base to the original
            // starting time.
            chronometer.base = chronometerViewModel.startTime!!
        }

        chronometer.start()
    }
}