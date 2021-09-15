package com.example.stopwatch

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class MainActivity : AppCompatActivity() {
    lateinit var start : Button
    lateinit var reset : Button
    lateinit var timer : Chronometer
    lateinit var layout : ConstraintLayout
    var on = false
    var time = 0L

    companion object{
        val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start = findViewById(R.id.button_main_start)
        reset = findViewById(R.id.button_main_reset)
        timer = findViewById(R.id.chronometer_main_timer)
        layout = findViewById(R.id.layout_main)
        start.textSize = 20f
        reset.textSize = 20f
        start.setBackgroundColor(Color.rgb(0f, 0.6f, 0f))
        layout.setBackgroundColor(Color.rgb(0.95f, 1f, 1f))

        start.setOnClickListener {
            if(on) {
                timer.stop()
                time = SystemClock.elapsedRealtime() - timer.base
                start.text = "Start"
                start.setBackgroundColor(Color.rgb(0f,0.6f,0f))
            }
            else {
                timer.start()
                timer.base = SystemClock.elapsedRealtime() - time
                start.text = "Stop"
                start.setBackgroundColor(Color.rgb(1f,0.5f,0.5f))
            }
            on = !on
        }
        reset.setOnClickListener {
            if(on){
                start.callOnClick()
            }
            time = 0L
            timer.base = SystemClock.elapsedRealtime()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(on) {
            time = SystemClock.elapsedRealtime() - timer.base
        }
        outState.putLong("saveTime",time)
        outState.putBoolean("saveOn",on)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        time = savedInstanceState.getLong("saveTime")
        on = savedInstanceState.getBoolean("saveOn")
        timer.base = SystemClock.elapsedRealtime() - time
        if(on){
            timer.start()
            start.text = "Stop"
            start.setBackgroundColor(Color.rgb(1f,0.5f,0.5f))
        }
    }







    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
        start = findViewById(R.id.button_main_start)
        reset = findViewById(R.id.button_main_reset)
        timer = findViewById(R.id.chronometer_main_timer)
        layout = findViewById(R.id.layout_main)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
}