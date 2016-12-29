package com.cloudwalkdigital.activation.evaluationapp.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.activities.LoginActivity;

/**
 * Created by henry on 14/07/2016.
 */
public class EvalService extends Service {
    int numMessages = 1;
    SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;
    public static String NOTIF_INFO = "NOTIF_INFO";
    public static String FROM_NOTIF = "FROM_NOTIF";
    public static final String PREFS_NAME = "MyPref";

    public EvalService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        sharedPreference = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        NOTIF_INFO = sharedPreference.getString("notif", "");
        numMessages++;
//        Toast.makeText(this, "Service was Created", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart(Intent intent, int startId) {
//        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
            Intent resultIntent = new Intent(this, LoginActivity.class);
            resultIntent.putExtra("from_notif", "yes");
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP |
                Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0,
                    resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder mNotifyBuilder;
            NotificationManager mNotificationManager;
            mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            // Sets an ID for the notification, so it can be updated
            int notifyID = 9001;
            mNotifyBuilder = new NotificationCompat.Builder(this)
                    .setContentTitle("Evaluation App Alert")
                    .setContentText("New record has been added please sync your device.")
                    .setSmallIcon(R.mipmap.ic_launcher);
            // Set pending intent
            mNotifyBuilder.setContentIntent(resultPendingIntent);
            // Set Vibrate, Sound and Light
            int defaults = 0;
            defaults = defaults | Notification.DEFAULT_LIGHTS;
            defaults = defaults | Notification.DEFAULT_VIBRATE;
            defaults = defaults | Notification.DEFAULT_SOUND;
            mNotifyBuilder.setDefaults(defaults);
            // Set the content for Notification
            mNotifyBuilder.setContentText("New record has been added please sync your device.");
            // Set autocancel
            mNotifyBuilder.setAutoCancel(true);
            // Post a notification
            mNotificationManager.notify(notifyID, mNotifyBuilder.build());
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();

    }
}
