package com.nguyen.codelabandroidlifecycles

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var liveDataTimerViewModel: LiveDataTimerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        liveDataTimerViewModel = ViewModelProvider(this)[LiveDataTimerViewModel::class.java]

        subscribe()
    }

    private fun subscribe() {
        val elapsedTimeObserver = Observer<Long> { value ->
            val newText = resources.getString(R.string.seconds, value)
            (findViewById<View>(R.id.timer_textview) as TextView).text = newText
            Log.d("ChronoActivity3", "Updating timer")
        }

        // observe the ViewModel's elapsed time
        liveDataTimerViewModel.elapsedTime.observe(this, elapsedTimeObserver)
    }
}