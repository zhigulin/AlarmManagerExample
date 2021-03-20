package com.example.interview

import android.app.AlarmManager
import android.app.Service
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ExampleAlarm.setupAlarm(this)
        //setContentView(R.layout.activity_main)
        finish()
    }
}