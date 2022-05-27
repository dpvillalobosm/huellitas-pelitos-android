package co.edu.udistrital.espingsw.huellitaspelitos.data.service

import me.pushy.sdk.Pushy
import android.content.Intent
import android.graphics.Color
import android.content.Context
import android.app.PendingIntent
import android.media.RingtoneManager
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Bundle
import androidx.core.app.NotificationCompat
import co.edu.udistrital.espingsw.huellitaspelitos.R
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Constants
import co.edu.udistrital.espingsw.huellitaspelitos.ui.serviceflow.client.StartEstheticistActivity
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.NotificationType
import co.edu.udistrital.espingsw.huellitaspelitos.ui.login.LoginActivity
import co.edu.udistrital.espingsw.huellitaspelitos.ui.serviceflow.client.ContinueServicesActivity
import co.edu.udistrital.espingsw.huellitaspelitos.ui.serviceflow.client.EndServicesActivity
import co.edu.udistrital.espingsw.huellitaspelitos.ui.serviceflow.estheticist.ContinueServiceForPetActivity
import co.edu.udistrital.espingsw.huellitaspelitos.ui.serviceflow.estheticist.EndServicesEstheticistActivity
import co.edu.udistrital.espingsw.huellitaspelitos.ui.serviceflow.estheticist.StartServicesActivity
import co.edu.udistrital.espingsw.huellitaspelitos.ui.serviceflow.estheticist.VisitCreatedByPetOwnerActivity

class PushReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Attempt to extract the "title" property from the data payload, or fallback to app shortcut label
        val notificationTitle =
            if (intent.getStringExtra("title") != null) intent.getStringExtra("title") else context.packageManager.getApplicationLabel(
                context.applicationInfo
            ).toString()

        // Attempt to extract the "message" property from the data payload: {"message":"Hello World!"}
        val notificationText =
            if (intent.getStringExtra("message") != null) intent.getStringExtra("message") else "Test notification"

        //If we want to receive more data from notification, get them here: https://pushy.me/docs/android/parse-notification-data
        val notificationType = intent.getIntExtra("type", 1)
        val notificationDestination = intent.getIntExtra("destination", 0)
        val photoForNotification =
            if (intent.getStringExtra("photo") != null) intent.getStringExtra("photo") else ""
        val visitId = intent.getIntExtra("visitId", 0)

        //Create intents for going to view onClick on notification.
        var pendingIntent: PendingIntent = PendingIntent.getActivity(
            context,
            0,
            Intent(context, LoginActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        when (notificationType) {
            //Client has started the visit
            NotificationType.START_CLIENT.type -> {

                pendingIntent = if (notificationDestination == 0) {
                    PendingIntent.getActivity(
                        context, 0, Intent(
                            context,
                            LoginActivity::class.java
                        ).apply { putExtra(Constants.VISIT_ID, visitId) },
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                    )
                } else {
                    PendingIntent.getActivity(
                        context, 0, Intent(
                            context,
                            VisitCreatedByPetOwnerActivity::class.java
                        ).apply { putExtra(Constants.VISIT_ID, visitId) },
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                    )
                }

            }

            //Estheticist has confirmed the visit
            NotificationType.START_ESTHETICIST.type -> {

                pendingIntent = PendingIntent.getActivity(
                    context, 0, Intent(
                        context,
                        StartEstheticistActivity::class.java
                    ).apply {
                        putExtra(Constants.VISIT_ID, visitId)
                        putExtra(Constants.PHOTO, photoForNotification)
                    },
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            }

            //Estheticist has started the services
            NotificationType.CONTINUE_SERVICE.type -> {

                pendingIntent = PendingIntent.getActivity(
                    context, 0, Intent(
                        context,
                        ContinueServicesActivity::class.java
                    ).apply {
                        putExtra(Constants.VISIT_ID, visitId)
                        putExtra(Constants.PHOTO, photoForNotification)
                    },
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            }

            //Estheticist has finished the services
            NotificationType.FINISH_SERVICES.type -> {

                pendingIntent = PendingIntent.getActivity(
                    context, 0, Intent(
                        context,
                        EndServicesActivity::class.java
                    ).apply {
                        putExtra(Constants.VISIT_ID, visitId)
                        putExtra(Constants.PHOTO, photoForNotification)
                    },
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            }
        }

        // Prepare a notification with vibration, sound and lights
        val builder = NotificationCompat.Builder(context)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_stat_ic_notification)
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            .setLights(Color.RED, 1000, 1000)
            .setVibrate(longArrayOf(0, 400, 250, 400))
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setContentIntent(pendingIntent)

        // Automatically configure a Notification Channel for devices running Android O+
        Pushy.setNotificationChannel(builder, context)

        // Get an instance of the NotificationManager service
        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        // Build the notification and display it
        notificationManager.notify(1, builder.build())
    }
}