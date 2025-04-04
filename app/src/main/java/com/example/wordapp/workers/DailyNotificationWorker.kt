package com.example.wordapp.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.wordapp.MainActivity
import com.example.wordapp.R
import java.util.concurrent.TimeUnit

class DailyNotificationWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        sendNotification()
        return Result.success()
    }

    private fun sendNotification() {
        val channelId = "word_reminder_channel"
        val notificationId = 1

        val notificationManager = applicationContext
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Word Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Instant word reminders"
            }
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Үг цээжлэх цаг боллоо")
            .setContentText("Өнөөдрийн шинэ үгээ сурцгаая!")
            .setPriority(NotificationCompat.PRIORITY_HIGH) // Changed to high priority
            .setContentIntent(createNotificationIntent())
            .setAutoCancel(true)
            .setOngoing(true) // Makes notification non-dismissable
            .setOnlyAlertOnce(true) // Only play sound/vibrate first time
            .build()

        notificationManager.notify(notificationId, notification)
    }

    private fun createNotificationIntent(): PendingIntent {
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("from_notification", true)
        }
        return PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    companion object {
        fun enqueue(context: Context) {
            // Note: Minimum interval for PeriodicWorkRequest is 15 minutes
            // For more frequent notifications, consider using AlarmManager
            val workRequest = PeriodicWorkRequestBuilder<DailyNotificationWorker>(
                15, // Minimum interval allowed by WorkManager
                TimeUnit.MINUTES
            )
                .setInitialDelay(1, TimeUnit.SECONDS)
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "instant_word_notification",
                ExistingPeriodicWorkPolicy.REPLACE,
                workRequest
            )
        }
    }
}


