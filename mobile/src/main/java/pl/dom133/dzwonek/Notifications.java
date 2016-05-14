package pl.dom133.dzwonek;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by dom133 on 24.03.2016.
 */
public class Notifications {

    Application app;
    int minuts;

    public Notifications(Application app, int minuts)
    {
        this.app = app;
        this.minuts = minuts;
    }

    public void cancleNotification()
    {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(app);
        notificationManager.cancel(1);
    }

    public void sendNotification(String title, String txt)
    {
        switch(minuts) {
            case 10:
            {
                Notification notification = new NotificationCompat.Builder(app)
                        .setSmallIcon(R.drawable.mr_ic_play_light)
                        .setPriority(2)
                        .setContentTitle(title)
                        .setContentText(txt)
                        .extend(new NotificationCompat.WearableExtender()
                                .setHintShowBackgroundOnly(true))
                        .setPriority(2)
                        .setVibrate(new long[] {1000, 1000, 1000, 1000, 1000})
                        .build();

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(app);
                int notificationId = 1;
                notificationManager.notify(notificationId, notification);
                break;
            }
            case 5:
            {
                Notification notification = new NotificationCompat.Builder(app)
                        .setSmallIcon(R.drawable.mr_ic_play_light)
                        .setPriority(2)
                        .setContentTitle(title)
                        .setContentText(txt)
                        .extend(new NotificationCompat.WearableExtender()
                                .setHintShowBackgroundOnly(true))
                        .setPriority(2)
                        .setVibrate(new long[] {1000, 1000})
                        .build();

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(app);
                int notificationId = 1;
                notificationManager.notify(notificationId, notification);
                break;
            }
            case 1:
            {
                Notification notification = new NotificationCompat.Builder(app)
                        .setSmallIcon(R.drawable.mr_ic_play_light)
                        .setPriority(2)
                        .setContentTitle(title)
                        .setContentText(txt)
                        .extend(new NotificationCompat.WearableExtender()
                                .setHintShowBackgroundOnly(true))
                        .setPriority(2)
                        .setVibrate(new long[] {5000})
                        .build();

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(app);
                int notificationId = 1;
                notificationManager.notify(notificationId, notification);
            }
            default: {
                Notification notification = new NotificationCompat.Builder(app)
                        .setSmallIcon(R.drawable.mr_ic_play_light)
                        .setPriority(2)
                        .setContentTitle(title)
                        .setContentText(txt)
                        .extend(new NotificationCompat.WearableExtender()
                                        .setHintShowBackgroundOnly(true))
                        .setPriority(2)
                        .build();

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(app);
                int notificationId = 1;
                notificationManager.notify(notificationId, notification);
            }
        }
    }
}
