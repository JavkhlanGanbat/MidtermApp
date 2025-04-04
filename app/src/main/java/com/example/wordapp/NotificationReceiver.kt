package com.example.wordapp.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.wordapp.workers.DailyNotificationWorker

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        DailyNotificationWorker.enqueue(context)
    }
}