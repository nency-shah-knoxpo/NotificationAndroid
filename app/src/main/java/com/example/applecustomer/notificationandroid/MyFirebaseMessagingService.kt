package com.example.applecustomer.notificationandroid

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.Uri
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.net.HttpURLConnection
import java.net.URL
import java.nio.channels.Channel
import android.app.NotificationChannel
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationManagerCompat
import android.support.v4.app.TaskStackBuilder
import java.util.*


class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String?) {

        Log.d("token", token)

    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        Log.e("msg", "arrived")
        val data = remoteMessage?.data
        val notificationBuilder: NotificationCompat.Builder?
        val random = Random()
        val rendomId = random.nextInt(1000+1)
        val GROUP_KEY_WORK_EMAIL = "com.android.example.NOTIFICATION"
        val SUMMARY_ID = 0

        val intent = Intent(this, Main2Activity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val CHANNEL_ID = "default"
        intent.putExtra("name", data?.get("name"))
        intent.putExtra("address", data?.get("address"))
        intent.putExtra("id", data?.get("id"))


        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(this).run {
            var addNextIntentWithParentStack = addNextIntentWithParentStack(intent)
           getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        // val pendingIntent = PendingIntent.getActivity(this, notification_id, intent, PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val contentViewSmall = RemoteViews(packageName, R.layout.notification_layout)

        contentViewSmall.setTextViewText(R.id.title, remoteMessage?.notification?.body)

        val bigview = RemoteViews(packageName, R.layout.notification_large)
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.knoxpo_bg)
        val bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.googleg_disabled_color_18)


        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.googleg_standard_color_18)
                .setLargeIcon(bitmap1)
                .setShowWhen(true)
                .setCustomContentView(contentViewSmall)
//                .setCustomBigContentView(bigview)
                .setSound(defaultSoundUri)
                .setStyle(NotificationCompat.BigPictureStyle()
                        .bigPicture(bitmap)
                        .bigLargeIcon(null))
                .setGroup(GROUP_KEY_WORK_EMAIL)
                .addAction(R.drawable.common_google_signin_btn_icon_dark, "Accept", resultPendingIntent)
                .addAction(R.drawable.common_google_signin_btn_icon_dark, "Decline", resultPendingIntent)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent)


        val summaryNotification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("summary")
                //set content text to support devices running API level < 24
                .setContentText("Two new messages")
                .setSmallIcon(R.drawable.ic_launcher_background)
                //build summary info into InboxStyle template
                .setStyle(NotificationCompat.InboxStyle()
                        .addLine("Alex Faarborg Check this out")
                        .addLine("Jeff Chang Launch Party")
                        .setBigContentTitle("2 new messages")
                        .setSummaryText("janedoe@example.com"))
                //specify which group this notification belongs to
                .setGroup(GROUP_KEY_WORK_EMAIL)
                //set this notification as the summary for the group
                .setGroupSummary(true)
                .build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notifyID = 1410
            val name = "default"// The user-visible name of the channel.
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            notificationManager.createNotificationChannel(mChannel)
        }
        notificationManager.notify(rendomId, notificationBuilder.build())
        NotificationManagerCompat.from(this).apply {
            notify(rendomId, notificationBuilder.build())
            notify(SUMMARY_ID, summaryNotification)
        }



    }




}
