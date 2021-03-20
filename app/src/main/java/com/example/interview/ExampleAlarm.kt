package com.example.interview

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.PowerManager
import androidx.core.content.ContextCompat

class ExampleAlarm {
    companion object {
        private val INTERVAL = AlarmManager.INTERVAL_HOUR

        private fun getCallIntent(context: Context): Intent {
            val permission = ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
            val intent = Intent()
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.action = if(permission != PackageManager.PERMISSION_GRANTED) Intent.ACTION_DIAL else Intent.ACTION_CALL
            intent.data = Uri.parse("tel:88005553535")
            return intent
        }

        fun setupAlarm(context: Context, idle: Boolean = false) {
            val manager = context.getSystemService(Service.ALARM_SERVICE) as AlarmManager
            val intent = getCallIntent(context)
            if (idle) {
                manager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis() + INTERVAL,
                        PendingIntent.getBroadcast(context, 0,
                                Intent(context, AlarmReceiver::class.java),
                                PendingIntent.FLAG_IMMUTABLE))

                context.startActivity(intent)
            } else {
                manager.setInexactRepeating(
                        AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + INTERVAL, INTERVAL,
                        PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
                )
            }
        }
    }
}