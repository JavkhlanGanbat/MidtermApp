package com.example.wordapp.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.wordapp.receivers.NotificationReceiver
import com.example.wordapp.workers.DailyNotificationWorker
import java.util.concurrent.TimeUnit

class NotificationHelper(private val context: Context) {

    fun scheduleNotification() {
        cancelExistingNotifications()

        // WorkManager-ээр 1 секундын дараа
        val workRequest = OneTimeWorkRequestBuilder<DailyNotificationWorker>()
            .setInitialDelay(1, TimeUnit.SECONDS)
            .build()

        WorkManager.getInstance(context).enqueue(workRequest)

        // AlarmManager-ээр 1 секундын дараа (backup)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val triggerTime = System.currentTimeMillis() + 1000 // 1 секундын дараа

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                triggerTime,
                pendingIntent
            )
        } else {
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                triggerTime,
                pendingIntent
            )
        }
    }

    fun cancelExistingNotifications() {
        WorkManager.getInstance(context).cancelAllWork()
    }
}