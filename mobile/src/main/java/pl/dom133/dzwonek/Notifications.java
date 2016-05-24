package pl.dom133.dzwonek;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class Notifications {

    Application app;
    int i = 0;

    public Notifications(Application app)
    {
        this.app = app;
    }

    public void cancleNotification()
    {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(app);
        notificationManager.cancel(1);
    }

    public void sendNotification(String title, String txt, int minuts)
    {
        Notification notification;
        if(minuts == 10 & i==0) {
            Log.i("NOTIFICATION", "1. "+minuts+":"+i);
            i++;
            notification = new NotificationCompat.Builder(app)
                    .setSmallIcon(R.drawable.mr_ic_play_light)
                    .setPriority(2)
                    .setContentTitle(title)
                    .setContentText(txt)
                    .extend(new NotificationCompat.WearableExtender()
                            .setHintShowBackgroundOnly(true))
                    .setPriority(2)
                    .setVibrate(new long[] {500, 3000})
                    .build();
        } else if(minuts == 5 & i==1) {
            Log.i("NOTIFICATION", "2. "+minuts+":"+i);
            i++;
            notification = new NotificationCompat.Builder(app)
                    .setSmallIcon(R.drawable.mr_ic_play_light)
                    .setPriority(2)
                    .setContentTitle(title)
                    .setContentText(txt)
                    .extend(new NotificationCompat.WearableExtender()
                            .setHintShowBackgroundOnly(true))
                    .setPriority(2)
                    .setVibrate(new long[] {500, 2000})
                    .build();
        } else if(minuts == 1 & i==2) {
            Log.i("NOTIFICATION", "3. "+minuts+":"+i);
            i=0;
            notification = new NotificationCompat.Builder(app)
                    .setSmallIcon(R.drawable.mr_ic_play_light)
                    .setPriority(2)
                    .setContentTitle(title)
                    .setContentText(txt)
                    .extend(new NotificationCompat.WearableExtender()
                            .setHintShowBackgroundOnly(true))
                    .setPriority(2)
                    .setVibrate(new long[] {500, 1000})
                    .build();
        } else {
             Log.i("NOTIFICATION", "4. "+minuts+":"+i);
             notification = new NotificationCompat.Builder(app)
                    .setSmallIcon(R.drawable.mr_ic_play_light)
                    .setPriority(2)
                    .setContentTitle(title)
                    .setContentText(txt)
                     .setVisibility(1)
                    .extend(new NotificationCompat.WearableExtender()
                            .setHintShowBackgroundOnly(true))
                    .setPriority(2)
                    .build();
        }
        notification.flags |= Notification.FLAG_NO_CLEAR;
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(app);
        int notificationId = 1;
        notificationManager.notify(notificationId, notification);
    }
}
